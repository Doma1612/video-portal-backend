/* (C)2023 */
package de.videoportal.video.dao;

import de.videoportal.video.entity.impl.Unterkategorie;
import jakarta.ejb.Stateless;
import java.util.Collection;

@Stateless
public class UnterkategorieDAO extends GenericDAO<Unterkategorie> {

    public UnterkategorieDAO() {
        super(Unterkategorie.class);
    }

    public void save(Unterkategorie unterk) {
        super.save(unterk);
    }

    public void delete(Unterkategorie unterk) {
        super.delete(unterk.getUnterkategorieId(), Unterkategorie.class);
    }

    public Collection<Unterkategorie> unterkategorienLaden() {
        return super.findAll();
    }
}
