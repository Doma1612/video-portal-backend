/* (C)2023-2024 */
package de.video.security;

import de.video.jwt.LogbackLogger;
import de.video.security.entity.User;
import de.video.security.facade.IUserFacade;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.logging.Logger;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class UserService {

    @Inject private IUserFacade userFacade;

    @Context private UriInfo uriInfo;

    @Inject @LogbackLogger private Logger logger;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) {
        System.out.println("login called with login: " + credentials.getUsername());
        User user;
        try {
            user = authenticate(credentials.getUsername(), credentials.getPassword());
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Benutzername oder Passwort falsch")
                    .build();
        }
        
        UserFrontend userDaten = new UserFrontend();
        userDaten.setName(user.getUsername());
        userDaten.setRolle(user.getRolle());
      
        return Response.ok(userDaten).build();
    }

    private User authenticate(String username, String password) throws Exception {
        User aUser = userFacade.findUserByName(username);
        if (aUser == null) {
            throw new SecurityException("Benutzer nicht gefunden");
        }
        System.out.println("authenticate called with username: " + username);
        System.out.println("Logged in User=" + aUser.getUsername());

        PlainSHA512PasswordHash hashAlgo = new PlainSHA512PasswordHash();
        if (aUser.getPassword().equals(hashAlgo.generate(password.toCharArray()))) {
            logger.info("### korrekte Authentifizierung ### ");
            logger.info(aUser.toString());
            return aUser;
        } else {
            System.out.println("Falsches Kennwort für Benutzer: " + aUser.getUsername());
            throw new SecurityException("Falscher Benutzername/Kennwort");
        }
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(Credentials credentials) {
        try {
            // Überprüfen, ob der Benutzer bereits existiert
            User existingUser = userFacade.findUserByName(credentials.getUsername());
            if (existingUser != null) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("Benutzername bereits vergeben")
                        .build();
            }

            // Erstellen eines neuen Benutzerobjekts
            User newUser = new User();
            newUser.setUsername(credentials.getUsername());
            PlainSHA512PasswordHash hashAlgo = new PlainSHA512PasswordHash();
            newUser.setPassword(hashAlgo.generate(credentials.getPassword().toCharArray()));
            newUser.setRolle(0);

            // Speichern des neuen Benutzers in der Datenbank
            userFacade.userAnlegen(newUser);

            return Response.ok("Benutzer erfolgreich registriert").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Registrierungsfehler")
                    .build();
        }
    }
}
