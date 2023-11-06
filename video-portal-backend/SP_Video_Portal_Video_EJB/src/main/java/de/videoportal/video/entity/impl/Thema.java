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

import de.videoportal.video.entity.ThemaTO;

@Entity
@Table(name = "Thema")
public class Thema {

    @Id
    @SequenceGenerator(name = "THEMA_ID", sequenceName = "THEMA_SEQ_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THEMA_ID")
    private long id;

    private String name;

    @OneToMany(mappedBy = "Thema")
    private List<Unterkategorie> unterkategorien;

    public Thema(long id, String name, List<Unterkategorie> unterkategorien) {
        super();
        this.id = id;
        this.name = name;
        this.unterkategorien = unterkategorien;
    }
    
    public Thema(long id, String name) {
    	super();
    	this.id = id;
    	this.name = name;
    }

    public ThemaTO toThemaTO() {
    	ThemaTO themaTo = new ThemaTO();
    	themaTo.setId(this.getId());
    	themaTo.setName(this.getName());
    	themaTo.setUnterkategorien(this.getKategorien());
    	
    	return themaTo;
    	
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

    public List<Unterkategorie> getKategorien() {
        return unterkategorien;
    }

    public void setKategorien(List<Unterkategorie> unterkategorien) {
        this.unterkategorien = unterkategorien;
    }
}
