/* (C)2023 */
package de.video.rest;

import de.videoportal.video.entity.VideoTO;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("video")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideoResource {

    private Logger logger = Logger.getLogger(VideoResource.class.getName());

    @POST
    @Path("videoHochladen")
    public Response videoHochloaden(VideoTO aVideoTO) {
        // verarbeite Bytstream
        return Response.ok().build();
    }
}
