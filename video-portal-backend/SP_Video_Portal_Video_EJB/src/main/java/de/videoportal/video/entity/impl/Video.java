/* (C)2023 */
package de.videoportal.video.entity.impl;

import de.videoportal.video.entity.VideoTO;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Video")
public class Video {

    @Id
    @SequenceGenerator(name = "VIDEO_ID", sequenceName = "VIDEO_SEQ_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIDEO_ID")
    private long id;

    private String titel;
    private String beschreibung;
    private String dateipfad;

    @ElementCollection private List<String> metaData;

    private int aufrufZaehler;

    @ManyToOne
    @JoinColumn(name = "KATEGORIE_ID")
    private Unterkategorie kategorie;

    public Video(
            Long id,
            String titel,
            String beschreibung,
            String dateipfad,
            List<String> metaData,
            int aufrufZaehler,
            Unterkategorie kategorie) {
        super();
        this.id = id;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.dateipfad = dateipfad;
        this.metaData = metaData;
        this.aufrufZaehler = aufrufZaehler;
        this.kategorie = kategorie;
    }

    public Video(
            Long id,
            String titel,
            String beschreibung,
            String dateipfad,
            List<String> metaData,
            int aufrufZaehler,
            long kategorieId,
            String name,
            Thema thema) {
        super();
        this.id = id;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.dateipfad = dateipfad;
        this.metaData = metaData;
        this.aufrufZaehler = aufrufZaehler;
        this.kategorie = new Unterkategorie(kategorieId, name, thema);
    }

    public VideoTO toVideoTO() {
        VideoTO videoTO = new VideoTO();
        videoTO.setVideoId(this.getId());
        videoTO.setTitel(this.getTitel());
        videoTO.setBeschreibung(this.getBeschreibung());
        videoTO.setDateipfad(this.getDateipfad());
        videoTO.setMetaData(this.getMetaData());
        videoTO.setAnzahlAufrufe(this.getAufrufZaehler());
        videoTO.setKategorieId(this.kategorie.getId());
        videoTO.setName(this.kategorie.getName());
        videoTO.setThema(this.kategorie.getThema());

        return videoTO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<String> getMetaData() {
        return metaData;
    }

    public void setMetaData(List<String> metaData) {
        this.metaData = metaData;
    }

    public int getAufrufZaehler() {
        return aufrufZaehler;
    }

    public void setAufrufZaehler(int aufrufZaehler) {
        this.aufrufZaehler = aufrufZaehler;
    }

    public Unterkategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Unterkategorie kategorie) {
        this.kategorie = kategorie;
    }
}
