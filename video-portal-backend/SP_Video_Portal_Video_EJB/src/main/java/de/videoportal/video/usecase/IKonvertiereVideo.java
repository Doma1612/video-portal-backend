package de.videoportal.video.usecase;

import de.videoportal.video.entity.VideoTO;
import jakarta.ejb.Local;

@Local
public interface IKonvertiereVideo {
	
	public boolean konvertiereVideo(VideoTO avideoTO);

}
