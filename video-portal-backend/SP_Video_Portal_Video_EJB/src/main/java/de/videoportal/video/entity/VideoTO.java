/* (C)2023 */
package de.videoportal.video.entity;

import de.videoportal.video.entity.impl.Thema;
import de.videoportal.video.entity.impl.Video;
import java.io.Serializable;
import java.util.List;

public class VideoTO implements Serializable {

    private static final long serialVersionUID = 8748026369117387931L;

    long videoId;
    String titel;
    String beschreibung;
    String dateipfad;
    List<String> metaData;
    int anzahlAufrufe;

    // Referenz zur Kategorie

    long kategorieId;
    String name;
    Thema thema;
    List<Video> videos;

    public VideoTO() {}

    public VideoTO(
            long videoId,
            String titel,
            String beschreibung,
            String dateipfad,
            List<String> metaData,
            int anzahlAufrufe,
            long kategorieId,
            String name,
            Thema thema,
            List<Video> videos) {
        super();
        this.videoId = videoId;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.dateipfad = dateipfad;
        this.metaData = metaData;
        this.anzahlAufrufe = anzahlAufrufe;
        this.kategorieId = kategorieId;
        this.name = name;
        this.thema = thema;
        this.videos = videos;
    }

    public Video toVideo() {
        Video video =
                new Video(
                        this.getVideoId(),
                        this.getTitel(),
                        this.getBeschreibung(),
                        this.getDateipfad(),
                        this.getMetaData(),
                        this.getAnzahlAufrufe(),
                        this.getKategorieId(),
                        this.getName(),
                        this.getThema(),
                        this.getVideos());
        return video;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getDateipfad() {
        return dateipfad;
    }

    public void setDateipfad(String dateipfad) {
        this.dateipfad = dateipfad;
    }

    public int getAnzahlAufrufe() {
        return anzahlAufrufe;
    }

    public void setAnzahlAufrufe(int anzahlAufrufe) {
        this.anzahlAufrufe = anzahlAufrufe;
    }

    public List<String> getMetaData() {
        return metaData;
    }

    public void setMetaData(List<String> metaData) {
        this.metaData = metaData;
    }

    public long getKategorieId() {
        return kategorieId;
    }

    public void setKategorieId(long kategorieId) {
        this.kategorieId = kategorieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Thema getThema() {
        return thema;
    }

    public void setThema(Thema thema) {
        this.thema = thema;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
