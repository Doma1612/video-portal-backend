/* (C)2023 */
package de.videoportal.video.entity;

import de.videoportal.video.entity.impl.Unterkategorie;

public class UnterkategorieTO {

    private static final long serialVersionUID = -3236541331415682968L;

    long unterkategorieId;
    String name;

    ThemaTO thema;

    public UnterkategorieTO() {}

    public UnterkategorieTO(long id, String name, ThemaTO thema) {
        this.unterkategorieId = id;
        this.name = name;
        this.thema = thema;
    }

    public UnterkategorieTO(long id, String name, long tid, String tname) {
        super();
        this.unterkategorieId = id;
        this.name = name;
        this.thema = new ThemaTO(tid, tname);
    }

    public Unterkategorie toUnterkategorie() {
        Unterkategorie unterkategorie =
                new Unterkategorie(this.getId(), this.getName(), this.getThema().toThema());
        return unterkategorie;
    }

    public long getId() {
        return unterkategorieId;
    }

    public void setId(long id) {
        this.unterkategorieId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThemaTO getThema() {
        return thema;
    }

    public void setThema(ThemaTO thema) {
        this.thema = thema;
    }
}
