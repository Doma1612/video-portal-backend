/* (C)2023 */
package de.videoportal.video.entity.impl;

import de.videoportal.video.entity.VideoTO;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "Video")
public class Video {

    @Id
    @SequenceGenerator(name = "VIDEO_ID", sequenceName = "VIDEO_SEQ_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIDEO_ID")
    private long videoId;

    private String titel;
    private String beschreibung;
    private String dateipfad;

    private String metaData;

    private int aufrufZaehler;

    private Thema thema;

    @ElementCollection
    @CollectionTable(name = "Unterkategorie", joinColumns = @JoinColumn(name = "videoId"))
    @Column(name = "unterkategorieId")
    private Collection<Long> unterKategorien;

    public Video() {}

    public Video(
            Long id,
            String titel,
            String beschreibung,
            String dateipfad,
            String metaData,
            int aufrufZaehler,
            Collection<Long> unterKategorien,
            Thema thema) {
        super();
        this.videoId = id;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.dateipfad = dateipfad;
        this.metaData = metaData;
        this.aufrufZaehler = aufrufZaehler;
        this.unterKategorien = unterKategorien;
        this.thema = thema;
    }

    public Video(
            Long id,
            String titel,
            String beschreibung,
            String dateipfad,
            String metaData,
            int aufrufZaehler,
            Collection<Long> unterKategorien,
            String name,
            Thema thema) {
        super();
        this.videoId = id;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.dateipfad = dateipfad;
        this.metaData = metaData;
        this.aufrufZaehler = aufrufZaehler;
        this.unterKategorien = unterKategorien;
        this.thema = thema;
    }

    public VideoTO toVideoTO() {
        VideoTO videoTO = new VideoTO();
        videoTO.setVideoId(this.getId());
        videoTO.setTitel(this.getTitel());
        videoTO.setBeschreibung(this.getBeschreibung());
        videoTO.setDateipfad(this.getDateipfad());
        videoTO.setMetaData(this.getMetaData());
        videoTO.setAnzahlAufrufe(this.getAufrufZaehler());
        videoTO.setUnterKategorien(this.getUnterKategorien());
        videoTO.setName(this.thema.getName());
        videoTO.setThema(this.getThema().toThemaTO());

        return videoTO;
    }

    public long getId() {
        return videoId;
    }

    public void setId(long id) {
        this.videoId = id;
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

    public int getAufrufZaehler() {
        return aufrufZaehler;
    }

    public void setAufrufZaehlerPlusOne(int aufrufZaehler) {
        this.aufrufZaehler++;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public Thema getThema() {
        return thema;
    }

    public void setThema(Thema kategorie) {
        this.thema = kategorie;
    }

    public Collection<Long> getUnterKategorien() {
        return unterKategorien;
    }

    public void setUnterKategorien(Collection<Long> unterKategorien) {
        this.unterKategorien = unterKategorien;
    }

    public void setAufrufZaehler(int aufrufZaehler) {
        this.aufrufZaehler = aufrufZaehler;
    }
}
