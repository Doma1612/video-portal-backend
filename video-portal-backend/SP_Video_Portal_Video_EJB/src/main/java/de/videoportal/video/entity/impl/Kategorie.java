/* (C)2023 */
package de.videoportal.video.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Kategorie")
public class Kategorie {

    @Id
    @SequenceGenerator(name = "KATEGORIE_ID", sequenceName = "KATEGORIE_SEQ_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KATEGORIE_ID")
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "THEMA_ID")
    private Thema thema;

    @OneToMany private List<Video> videos;

    public Kategorie(long id, String name, Thema thema, List<Video> videos) {
        super();
        this.id = id;
        this.name = name;
        this.thema = thema;
        this.videos = videos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
