/* (C)2023 */
package de.videoportal.video.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Thema")
public class Thema {

    @Id
    @SequenceGenerator(name = "THEMA_ID", sequenceName = "THEMA_SEQ_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THEMA_ID")
    private long id;

    private String name;

    @OneToMany(mappedBy = "thema")
    private List<Kategorie> kategorien;

    public Thema(long id, String name, List<Kategorie> kategorien) {
        super();
        this.id = id;
        this.name = name;
        this.kategorien = kategorien;
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

    public List<Kategorie> getKategorien() {
        return kategorien;
    }

    public void setKategorien(List<Kategorie> kategorien) {
        this.kategorien = kategorien;
    }
}
