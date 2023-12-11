/* (C)2023 */
package de.videoportal.video.entity;

import java.util.ArrayList;
import java.util.List;

import de.videoportal.video.entity.impl.Thema;
import de.videoportal.video.entity.impl.Unterkategorie;

public class ThemaTO {

    private static final long serialVersionUID = -4686845569436097883L;

    long id;
    String name;
    List<UnterkategorieTO> unterkategorien;

    public ThemaTO(long id, String name) {
        super();
        this.id = id;
        this.name = name;
           }

    public Thema toThema() {

        List<Unterkategorie> uks = new ArrayList<Unterkategorie>();
        for (UnterkategorieTO uk : this.unterkategorien) {
            uks.add(uk.toUnterkategorie());
        }
        Thema thema = new Thema(this.getId(), this.getName(), uks);
        return thema;
    }

    public ThemaTO() {}

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
    
    public List<UnterkategorieTO> getUnterkategorien() {
        return unterkategorien;
    }

    public void setUnterkategorien(List<UnterkategorieTO> unterkategorien) {

        this.unterkategorien = unterkategorien;
    }
    
}
