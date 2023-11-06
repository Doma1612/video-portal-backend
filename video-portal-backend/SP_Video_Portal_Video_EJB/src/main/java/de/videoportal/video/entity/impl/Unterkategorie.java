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

import de.videoportal.video.entity.UnterkategorieTO;

@Entity
@Table(name = "Unterkategorie")
public class Unterkategorie {

    @Id
    @SequenceGenerator(name = "UNTERKATEGORIE_ID", sequenceName = "UNTERKATEGORIE_SEQ_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UNTERKATEGORIE_ID")
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "THEMA_ID")
    private Thema thema;

    public Unterkategorie(long id, String name, Thema thema) {
        super();
        this.id = id;
        this.name = name;
        this.thema = thema;
    }
    
    public Unterkategorie(long id, String name, long tid, String tname, List<Unterkategorie> unterkategorien) {
        super();
        this.id = id;
        this.name = name;
        this.thema = new Thema(tid,tname,unterkategorien);
    }
    
    public UnterkategorieTO toUnterkategorieTO() {
    	UnterkategorieTO unterkategorieTO = new UnterkategorieTO();
    	unterkategorieTO.setId(this.getId());
    	unterkategorieTO.setName(this.getName());
    	unterkategorieTO.setThema(this.getThema());
    	
    	return unterkategorieTO;
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

}
