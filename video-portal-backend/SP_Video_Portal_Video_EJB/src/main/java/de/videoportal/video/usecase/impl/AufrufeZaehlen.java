package de.videoportal.video.usecase.impl;

import de.videoportal.video.dao.VideoDAO;
import de.videoportal.video.entity.VideoTO;
import de.videoportal.video.entity.impl.Video;
import de.videoportal.video.usecase.IAufrufeZaehlen;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class AufrufeZaehlen implements IAufrufeZaehlen{
	
	@Inject VideoDAO videoDAO;

	@Override
	public void aufrufeVideoUpdaten(VideoTO videoTO) {
		Video video = videoDAO.find(videoTO.getVideoId());
		video.setAufrufZaehler(videoTO.getAnzahlAufrufe()+ 1);
	
		videoDAO.update(video);
	}

	@Override
	public void aufrufeVideoLaden(VideoTO videoTO) {
		// TODO Auto-generated method stub
		
	}

}
