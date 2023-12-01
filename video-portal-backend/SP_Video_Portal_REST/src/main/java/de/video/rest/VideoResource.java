/* (C)2023 */
package de.video.rest;

import de.videoportal.video.usecase.IKonvertiereVideo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

@Path("video")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideoResource {

    private Logger logger = Logger.getLogger(VideoResource.class.getName());

    @Inject IKonvertiereVideo converter;

    @POST
    @Path(
            "videoHinzufuegen/{dateiEndung}/{titel}/{thema}/{beschreibung}/{stichwoerter}/{unterkategorien}")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response videoDatenHinzufuegen(
            @PathParam("dateiEndung") String dateiEndung,
            @PathParam("titel") String titel,
            @PathParam("thema") String thema,
            @PathParam("stichwoerter") String stichwoerter,
            @PathParam("unterkategorien") String unterkategorien,
            InputStream videoStream) {
        byte[] videoBytes = null;
        try {
            videoBytes =
                    IOUtils.toByteArray(videoStream); // IOUtils aus Apache Commons IO verwenden
            logger.warning("Videobytes wurden eingelesen");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle Fehler beim Lesen des Streams
        }
        // interface aufrufen und video in Usecase konvertieren und speichern.
        boolean anlegenUndKonvertierenHatGeklappt =
                converter.empfangeVideoDaten(
                        dateiEndung, titel, thema, stichwoerter, unterkategorien, videoBytes);
        if (anlegenUndKonvertierenHatGeklappt) {
            return Response.ok().build();
        } else {
            return Response.status(
                            404,
                            "Fehler beim Upload und Konvertierend es Videos. Bitte in die Payara"
                                    + " Logs schauen!")
                    .build();
        }
    }
}
