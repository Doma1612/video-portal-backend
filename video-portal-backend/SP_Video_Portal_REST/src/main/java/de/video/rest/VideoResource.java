/* (C)2023 */
package de.video.rest;

import de.video.security.JWTTokenNeeded;
import de.video.security.Role;
import de.videoportal.video.entity.ThemaTO;
import de.videoportal.video.entity.UnterkategorieTO;
import de.videoportal.video.entity.VideoTO;
import de.videoportal.video.usecase.IAufrufeZaehlen;
import de.videoportal.video.usecase.IThemaVerwalten;
import de.videoportal.video.usecase.IUnterkategorieVerwalten;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
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

    @Inject IThemaVerwalten themaVerwalten;

    @Inject IUnterkategorieVerwalten uKategorieVerwalten;
    
    @Inject IAufrufeZaehlen aufrufeZaehlen;

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
    
    // Themen verwalten

    @POST
    @Path("themaAnlegen")
    // @JWTTokenNeeded(Permissions = Role.ADMIN)
    public Response themaAnlegen(ThemaTO themaTO) {
        themaVerwalten.themaAnlegen(themaTO);
        return Response.ok().build();
    }

    @POST
    @Path("themaUpdate")
    // @JWTTokenNeeded(Permissions = Role.ADMIN)
    public Response themaUpdate(ThemaTO themaTO) {
        themaVerwalten.themaUpdaten(themaTO);
        return Response.ok().build();
    }

    @GET
    @Path("themaLoeschen/{id}")
    public Response themaLoeschen(@PathParam("id") long id) {
        themaVerwalten.themaLoeschen(id);
        return Response.ok().build();
    }
    
    // Unterkategorien verwalten

    @POST
    @Path("uKategorieAnlegen")
    // @JWTTokenNeeded(Permissions = Role.ADMIN)
    public Response uKategorieAnlegen(UnterkategorieTO unterkategorieTO) {
        uKategorieVerwalten.unterkategorieAnlegen(unterkategorieTO);
        return Response.ok().build();
    }

    @POST
    @Path("uKategorieUpdate")
    // @JWTTokenNeeded(Permissions = Role.ADMIN)
    public Response uKategorieUpdate(UnterkategorieTO unterkategorieTO) {
        uKategorieVerwalten.unterkategorieAnlegen(unterkategorieTO);
        return Response.ok().build();
    }

    @GET
    @Path("uKategorieLoeschen/{id}")
    // @JWTTokenNeeded(Permissions = Role.ADMIN)
    public Response uKategorieLoeschen(@PathParam("id") long id) {
        uKategorieVerwalten.unterkategorieLoeschen(id);
        return Response.ok().build();
    }
    
    // Videos verwalten
    
    @POST
    @Path("videoAufrufeZahlen")
    public Response videoAufrufe(VideoTO videoTO) {
      //  try {
            aufrufeZaehlen.aufrufeVideoUpdaten(videoTO);
            return Response.ok().build();
     //   } catch (Exception e) {
       //     return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        //}
    }

    
}
