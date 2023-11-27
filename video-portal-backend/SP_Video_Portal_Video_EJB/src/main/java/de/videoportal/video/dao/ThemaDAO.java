/* (C)2023 */
package de.videoportal.video.dao;

import de.videoportal.video.entity.impl.Thema;
import jakarta.ejb.Stateless;
import java.util.Collection;

@Stateless
public class ThemaDAO extends GenericDAO<Thema> {

    public ThemaDAO() {
        super(Thema.class);
    }

    public void save(Thema thema) {
        super.save(thema);
    }

    public void delete(Thema thema) {
        super.delete(thema.getThemaId(), Thema.class);
    }

    public Collection<Thema> themenLaden() {
        return super.findAll();
    }
}
