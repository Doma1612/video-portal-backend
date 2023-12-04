/* (C)2023 */
package de.videoportal.video.usecase;

import de.videoportal.video.entity.VideoTO;
import java.util.List;

public interface ILadeVideo {

    public byte[] ladeVideo(long id);

    public List<VideoTO> ladeVideosNachSuche(String stichwoerter);
}
