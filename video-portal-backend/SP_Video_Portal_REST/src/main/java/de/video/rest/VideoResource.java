/* (C)2023-2024 */
package de.video.rest;

import de.videoportal.video.entity.ThemaTO;
import de.videoportal.video.entity.UnterkategorieTO;
import de.videoportal.video.entity.VideoTO;
import de.videoportal.video.usecase.IKonvertiereVideo;
import de.videoportal.video.usecase.ILadeVideo;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

@Path("video")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideoResource {

    private Logger logger = Logger.getLogger(VideoResource.class.getName());
    @Inject IThemaVerwalten themaVerwalten;

    @Inject IUnterkategorieVerwalten unterkategorieVerwalten;

    @Inject IKonvertiereVideo converter;

    @Inject ILadeVideo videoLoader;

    // Themen verwalten

    @POST
    @Path("themaAnlegen")
    public Response themaAnlegen(ThemaTO themaTO) {
        themaVerwalten.themaAnlegen(themaTO);
        return Response.ok().build();
    }

    @POST
    @Path("themaUpdate")
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

    @GET
    @Path("ladeAlleThemen")
    public List<ThemaTO> ladeAlleThemen() {
        return (List<ThemaTO>) themaVerwalten.ladeAlleThemen();
    }

    @POST
    @Path("uKategorieAnlegen")
    public Response uKategorieAnlegen(UnterkategorieTO unterkategorieTO) {
        unterkategorieVerwalten.unterkategorieAnlegen(unterkategorieTO);
        return Response.ok().build();
    }

    @POST
    @Path("uKategorieUpdate")
    public Response uKategorieUpdate(UnterkategorieTO unterkategorieTO) {
        unterkategorieVerwalten.unterkategorieAnlegen(unterkategorieTO);
        return Response.ok().build();
    }

    @GET
    @Path("uKategorieLoeschen/{id}")
    public Response uKategorieLoeschen(@PathParam("id") long id) {
        unterkategorieVerwalten.unterkategorieLoeschen(id);
        return Response.ok().build();
    }

    @GET
    @Path("ladeAlleUnterkategorien")
    public List<UnterkategorieTO> ladeAlleUnterkategorien() {
        return (List<UnterkategorieTO>) unterkategorieVerwalten.ladeAlleUnterkategorien();
    }

    // Videos verwalten

    @POST
    @Path(
            "videoHinzufuegen/{dateiEndung}/{titel}/{thema}/{beschreibung}/{stichwoerter}/{unterkategorien}")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response videoDatenHinzufuegen(
            @PathParam("dateiEndung") String dateiEndung,
            @PathParam("titel") String titel,
            @PathParam("thema") String thema,
            @PathParam("beschreibung") String beschreibung,
            @PathParam("stichwoerter") String stichwoerter,
            @PathParam("unterkategorien") String unterkategorien,
            InputStream videoStream) {
        byte[] videoBytes = null;
        try {
            videoBytes = IOUtils.toByteArray(videoStream);
            logger.warning("Videobytes wurden eingelesen");
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean anlegenUndKonvertierenHatGeklappt =
                converter.empfangeVideoDaten(
                        dateiEndung,
                        titel,
                        thema,
                        beschreibung,
                        stichwoerter,
                        unterkategorien,
                        videoBytes);
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

    @GET
    @Path("ladeVideo/{id}")
    @Produces("video/webm")
    public Response ladeVideo(@PathParam("id") long id) {
        byte[] videoBytes = videoLoader.ladeVideo(id);
        return Response.ok(videoBytes, MediaType.APPLICATION_OCTET_STREAM).build();
    }

    @GET
    @Path("ladeVideosNachSuche/{stichwoerter}")
    public List<VideoTO> ladeVideosNachSuche(@PathParam("stichwoerter") String stichwoerter) {
        return videoLoader.ladeVideosNachSuche(stichwoerter);
    }
}
