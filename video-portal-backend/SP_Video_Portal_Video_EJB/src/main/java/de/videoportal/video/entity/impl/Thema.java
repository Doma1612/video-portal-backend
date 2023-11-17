/* (C)2023 */
package de.videoportal.video.entity.impl;

import de.videoportal.video.entity.ThemaTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "Thema")
public class Thema {

    @Id
    @SequenceGenerator(name = "THEMA_ID", sequenceName = "THEMA_SEQ_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THEMA_ID")
    private long themaId;

    private String name;

    @OneToMany private Collection<Unterkategorie> kategorien = new ArrayList<Unterkategorie>();

    public Thema(long id, String name, Collection<Unterkategorie> uks) {
        super();
        this.themaId = id;
        this.name = name;
        this.kategorien = uks;
    }

    public ThemaTO toThemaTO() {
        ThemaTO themaTo = new ThemaTO();
        themaTo.setId(this.getThemaId());
        themaTo.setName(this.getName());

        return themaTo;
    }

    public Thema() {}

    public long getThemaId() {
        return themaId;
    }

    public void setThemaId(long id) {
        this.themaId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Unterkategorie> getKategorien() {
        return kategorien;
    }

    public void setKategorien(Collection<Unterkategorie> kategorien) {
        this.kategorien = kategorien;
    }

    public void addUnterkategorie(Unterkategorie uK) {
        this.kategorien.add(uK);
    }

    public void removeUnterkategorie(Unterkategorie uK) {
        this.kategorien.remove(uK);
    }
}
