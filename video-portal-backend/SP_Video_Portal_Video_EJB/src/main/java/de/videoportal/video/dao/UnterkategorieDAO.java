/* (C)2023 */
package de.videoportal.video.dao;

import de.videoportal.video.entity.impl.Unterkategorie;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class UnterkategorieDAO extends GenericDAO<Unterkategorie> {

    public UnterkategorieDAO() {
        super(Unterkategorie.class);
    }

    public void save(Unterkategorie aUnterkategorie) {
        super.save(aUnterkategorie);
    }

    public boolean delete(long aUnterkategorieId) {
        return super.delete(aUnterkategorieId, Unterkategorie.class);
    }

    public void update(Unterkategorie aUnterkategorie) {
        super.update(aUnterkategorie);
    }

    public List<Unterkategorie> findAll() {
        return super.findAll();
    }

    public List<Unterkategorie> findAllUnterKategorien() {
        return super.findAll();
    }
}
