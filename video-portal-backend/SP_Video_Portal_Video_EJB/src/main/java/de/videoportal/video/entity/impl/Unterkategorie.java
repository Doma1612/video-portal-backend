/* (C)2023 */
package de.videoportal.video.entity.impl;

import de.videoportal.video.entity.UnterkategorieTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Unterkategorie")
public class Unterkategorie {

    @Id
    @SequenceGenerator(
            name = "UNTERKATEGORIE_ID",
            sequenceName = "UNTERKATEGORIE_SEQ_ID",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UNTERKATEGORIE_ID")
    private long unterkategorieId;

    private String name;

    @ManyToOne private Thema thema;

    public Unterkategorie(long id, String name, Thema thema) {
        super();
        this.unterkategorieId = id;
        this.name = name;
        this.thema = thema;
    }

    public Unterkategorie(
            long id, String name, long tid, String tname, List<Unterkategorie> unterkategorien) {
        super();
        this.unterkategorieId = id;
        this.name = name;
        this.thema = new Thema(tid, tname, unterkategorien);
    }

    public Unterkategorie() {}

    public UnterkategorieTO toUnterkategorieTO() {
        UnterkategorieTO unterkategorieTO =
                new UnterkategorieTO(
                        this.getUnterkategorieId(), this.getName(), this.getThema().toThemaTO());
        return unterkategorieTO;
    }

    public long getUnterkategorieId() {
        return unterkategorieId;
    }

    public void setUnterkategorieId(long id) {
        this.unterkategorieId = id;
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
}
