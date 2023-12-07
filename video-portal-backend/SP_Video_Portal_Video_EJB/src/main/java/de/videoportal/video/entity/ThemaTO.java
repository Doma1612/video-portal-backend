/* (C)2023 */
package de.videoportal.video.entity;

import de.videoportal.video.entity.impl.Thema;

public class ThemaTO {

    private static final long serialVersionUID = -4686845569436097883L;

    long id;
    String name;

    public ThemaTO(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Thema toThema() {

        Thema thema = new Thema(this.getId(), this.getName());
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
}
