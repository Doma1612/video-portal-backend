package de.videoportal.video.usecase.impl;

import java.io.File;
import java.io.IOException;
import de.videoportal.video.dao.VideoDAO;
import de.videoportal.video.entity.VideoTO;
import de.videoportal.video.entity.impl.Video;
import de.videoportal.video.usecase.IKonvertiereVideo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.eclipse.persistence.internal.oxm.schema.model.List;


@Stateless
public class KonvertiereVideo implements IKonvertiereVideo{
	
	@Inject
	VideoDAO videoDAO;
	
	private static final Logger logger = Logger.getLogger(KonvertiereVideo.class.getName());
	
	@Override
	public boolean konvertiereVideo(VideoTO aVideoTO) {
		Video vid = aVideoTO.toVideo();
		String ffmpegPath = System.getProperty("user.dir") + "/src/main/resources/ffmpeg"; // Passe dies entsprechend an

        // Eingabe- und Ausgabedateien
        String inputFilepath = aVideoTO.getDateipfad(); // Passe dies entsprechend an
        String outputFilepath = inputFilepath.replace("initial_upload", "converted");
        String[] parts = outputFilepath.split("\\.");
        String fileExtension = parts[parts.length-1];
        outputFilepath = outputFilepath.replace("." + fileExtension, ".webm");
        
        // Baue den Befehl für FFmpeg
        String[] command = { ffmpegPath, "-i", inputFilepath, outputFilepath };
        try {
        	logger.warning("Konvertierung des folgenden Videos steht bevor: " + aVideoTO.getDateipfad());
			Runtime.getRuntime().exec(command);
			return true;
		} catch (IOException e) {
			logger.warning("Die Konvertierung des Videos mit ffmpeg ist fehlgeschlagen!");
			e.printStackTrace();
			return false;
		}	
	}
	public boolean videoHochladen(VideoTO aVideoTO) {
		videoDatenInDBSpeichern(aVideoTO);
		legeVideoInDateiSystemAb(aVideoTO);
		return konvertiereVideo(aVideoTO);
		
	}
	
	private void videoDatenInDBSpeichern(VideoTO aVideoTO) {
		Video vid = aVideoTO.toVideo();
		videoDAO.save(vid);
	}
	
	private void legeVideoInDateiSystemAb(VideoTO aVideoTO) {
		String directoryPath = "/home/kuehling/Dokumente/sp_videos/initial_upload/";
		ArrayList<Video> vids = (ArrayList<Video>) videoDAO.findAll();
		for (Video vid: vids ) {
			if (vid.getBeschreibung().equals(aVideoTO.getBeschreibung())
					&& vid.getMetaData().equals(aVideoTO.getMetaData())
					&& vid.getTitel().equals(aVideoTO.getTitel())) {
				directoryPath += vid.getId(); 
				File directory = new File(directoryPath+"/"+vid.getId());
				if (!directory.exists()) {
					boolean success = directory.mkdirs();
					if (success) {
						logger.warning("Anlegen des Ordners mit folgender ID war erfolgreich: " + vid.getId());
					}
					else {
						logger.warning("Fehler beim Anlegen des Ordners für die VideoId: " + vid.getId());
					}
				}
				else {
					logger.warning("Ordner exisitert bereits.");
				}
				// idee: initialer dateipfad bei erstem hochladen ist name des videos+endung.
				vid.setDateipfad(directoryPath+vid.getDateipfad());
				videoDAO.update(vid);
			}
		}
	}

}
