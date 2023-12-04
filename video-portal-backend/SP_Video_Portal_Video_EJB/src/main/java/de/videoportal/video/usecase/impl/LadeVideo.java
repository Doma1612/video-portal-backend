/* (C)2023 */
package de.videoportal.video.usecase.impl;

import de.videoportal.video.dao.VideoDAO;
import de.videoportal.video.entity.VideoTO;
import de.videoportal.video.entity.impl.Video;
import de.videoportal.video.usecase.ILadeVideo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

@Stateless
public class LadeVideo implements ILadeVideo {

    @Inject VideoDAO videoDAO;
    private static final Logger logger = Logger.getLogger(KonvertiereVideo.class.getName());

    @Override
    public byte[] ladeVideo(long id) {
        Video vid = videoDAO.find(id);
        File vidPfad = new File(vid.getDateipfad());
        byte[] videoBytes = null;
        try {
            videoBytes =
                    FileUtils.readFileToByteArray(
                            vidPfad); // IOUtils aus Apache Commons IO verwenden
            logger.warning("Videobytes wurden eingelesen");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle Fehler beim Lesen des Streams
        }
        vid.setAufrufZaehlerPlusOne();
        videoDAO.update(vid);
        return videoBytes;
    }

    @Override
    public List<VideoTO> ladeVideosNachSuche(String stichwoerter) {
        String[] stichwoerterList = stichwoerter.split(",\\s*");
        List<Video> allVids = videoDAO.findAll();
        List<VideoTO> videoList = new ArrayList<>();
        for (Video vid : allVids) {
            for (String stichwort : stichwoerterList) {
                if (vid.getMetaData().contains(stichwort)) {
                    VideoTO to = vid.toVideoTO();
                    to.setName(to.getName().replace("_", " "));
                    videoList.add(to);
                    logger.warning(
                            "Dieses Video wird an Rest gegeben: "
                                    + to.getDateipfad()
                                    + " und hat diesen Titel: "
                                    + to.getName());
                    break;
                }
            }
        }
        if (allVids.size() == 0) {
            logger.warning("Leere Liste wird zurückgegeben!");
        }
        return videoList;
    }
}
