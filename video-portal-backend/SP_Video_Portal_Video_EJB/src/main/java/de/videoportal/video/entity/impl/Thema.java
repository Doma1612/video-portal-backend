/* (C)2023 */
package de.videoportal.video.entity.impl;

import de.videoportal.video.entity.ThemaTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Thema")
public class Thema {

    @Id
    @SequenceGenerator(name = "THEMA_ID", sequenceName = "THEMA_SEQ_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THEMA_ID")
    private long themaId;

    private String name;

    public Thema(long id, String name) {
        super();
        this.themaId = id;
        this.name = name;
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
}
