/* (C)2023 */
package de.videoportal.video.dao;

import de.videoportal.video.entity.impl.Video;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class VideoDAO extends GenericDAO<Video> {
   
	public VideoDAO() {
        super(Video.class);
    }

    public void save(Video aVideo) {
        super.save(aVideo);
    }

    public boolean delete(long aVideoId) {
        return super.delete(aVideoId, Video.class);
    }

    public void update(Video aVideo) {
        super.update(aVideo);
    }

    public List<Video> findAll() {
        return super.findAll();
    }
}
