/* (C)2023 */
package de.video.rest;

import de.video.security.JWTTokenNeeded;
import de.video.security.Role;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("video")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideoResource {

    @POST
    @Path("aufrufeZaehlen/{videoId}")
    //	@JWTTokenNeeded(Permissions = Role.USER)
    public Response videoAufrufeZaehlen(@PathParam("id") long id) {

        // Hier wird die Schnittstelle aufgerufen

        return Response.ok().build();
    }

    @POST
    @Path("videoHinzufuegen/{video}/{titel}/{kategorie}/{beschreibung}/{stichwoerter}")
    @JWTTokenNeeded(Permissions = Role.ADMIN)
    public Response videoHinzufuegen(
            @PathParam("titel") String titel,
            @PathParam("kategorie") String kategorie,
            @PathParam("beschreibung") String beschreibung,
            @PathParam("stichwörter") String stichwörter) {

        // Hier wird die Schnittstelle aufgerufen

        return Response.ok().build();
    }
}
