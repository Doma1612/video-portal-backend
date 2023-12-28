/* (C)2023 */
package de.videoportal.video.usecase.impl;

import de.videoportal.video.dao.ThemaDAO;
import de.videoportal.video.dao.UnterkategorieDAO;
import de.videoportal.video.dao.VideoDAO;
import de.videoportal.video.entity.impl.Thema;
import de.videoportal.video.entity.impl.Unterkategorie;
import de.videoportal.video.entity.impl.Video;
import de.videoportal.video.usecase.IKonvertiereVideo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class KonvertiereVideo implements IKonvertiereVideo {

    @Inject VideoDAO videoDAO;
    @Inject UnterkategorieDAO unterkategorieDAO;
    @Inject ThemaDAO themaDAO;
    private static final Logger logger = Logger.getLogger(KonvertiereVideo.class.getName());

    @Override
    public boolean empfangeVideoDaten(
            String dateiEndung,
            String titel,
            String themaString,
            String beschreibung,
            String stichwoerter,
            String unterkategorien,
            byte[] videoBytes) {
        logger.warning(dateiEndung);
        logger.warning(titel);
        logger.warning(themaString);
        logger.warning(stichwoerter);
        logger.warning(unterkategorien);

        // Videobjekt erstellen und befuellen
        Video v = new Video();
        String vidTitel = titel + "." + dateiEndung;
        vidTitel = vidTitel.replace(" ", "_");
        // Leerzeichen im Titel für das Speichern auf der VM durch _ austauschen.
        // Beim spaeteren Auslesen wieder replace anwenden!
        v.setTitel(vidTitel);
        Thema thema = null;
        List<Thema> themen = themaDAO.findAll();
        for (Thema t : themen) {
            if (t.getName().toLowerCase().equals(themaString.toLowerCase())) {
                thema = t;
            }
        }
        v.setBeschreibung(beschreibung);
        v.setMetaData(stichwoerter);
        v.setThema(thema);
        String[] unterKategorienArray = unterkategorien.split(",\\s*");
        List<Unterkategorie> allUks = unterkategorieDAO.findAll();
        List<String> unterKategorienArrayList = new ArrayList<>(List.of(unterKategorienArray));
        List<Long> neededUks = new ArrayList<>();
        for (Unterkategorie uk : allUks) {
            if (unterKategorienArrayList.contains(uk.getName())) {
                neededUks.add(uk.getUnterkategorieId());
            }
        }
        v.setUnterKategorien(neededUks);

        videoDAO.save(v);
        List<Video> allVids = videoDAO.findAll();

        for (Video vid : allVids) {
            if (vid.getTitel().equals(vidTitel)) {
                v = vid;
            }
        }

        return konvertiereUndSpeichereVideo(videoBytes, v, dateiEndung);
    }

    @Override
    public boolean konvertiereUndSpeichereVideo(byte[] videoBytes, Video v, String dateiEndung) {
        String inputFilePath =
                String.format(
                        "/home/kuehling/Dokumente/sp_videos/initial_upload/%d/%s",
                        v.getId(), v.getTitel());
        String outputFilePath = inputFilePath.replace("." + dateiEndung, ".webm");
        outputFilePath = outputFilePath.replace("initial_upload", "converted");
        logger.warning("Lege Ordnerstrukturen an fuer: " + inputFilePath);
        legeOrdnerStrukturAn(inputFilePath);
        logger.warning("Lege Ordnerstrukturen an fuer: " + outputFilePath);
        legeOrdnerStrukturAn(outputFilePath);
        // bytes in Datei schreiben.
        FileOutputStream out;
        try {
            out = new FileOutputStream(inputFilePath);
            out.write(videoBytes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String ffmpegPath = "/home/kuehling/Dokumente/sp_videos/ffmpeg";
        logger.warning("Pfad zur ffmpeg datei: " + ffmpegPath);
        // Baue den Befehl für FFmpeg
        String[] command = {ffmpegPath, "-i", inputFilePath, outputFilePath};
        try {
            logger.warning("Konvertierung des folgenden Videos steht bevor: " + inputFilePath);
            Runtime.getRuntime().exec(command);
            // Konvertierten Pfad im Videoobjekt hinterlegen und in der DB aktualisieren.
            v.setDateipfad(outputFilePath);
            videoDAO.update(v);
            return true;
        } catch (IOException e) {
            logger.warning("Die Konvertierung des Videos mit ffmpeg ist fehlgeschlagen!");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void legeOrdnerStrukturAn(String path) {

        File directory = new File(path);
        if (!directory.getParentFile().exists()) {
            boolean success = directory.getParentFile().mkdirs();
            if (success) {
                logger.warning("Anlegen des Ordners fuer folgende Datei war erfolgreich: " + path);
            } else {
                logger.warning(
                        "Fehler beim Anlegen des Parentordners fuer folgende Datei: " + path);
            }
        } else {
            logger.warning("Ordner exisitert bereits.");
        }
    }
}
