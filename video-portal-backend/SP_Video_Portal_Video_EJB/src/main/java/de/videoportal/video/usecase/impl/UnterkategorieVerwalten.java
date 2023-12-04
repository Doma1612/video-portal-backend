/* (C)2023 */
package de.videoportal.video.usecase.impl;

import de.videoportal.video.dao.UnterkategorieDAO;
import de.videoportal.video.entity.UnterkategorieTO;
import de.videoportal.video.entity.impl.Unterkategorie;
import de.videoportal.video.usecase.IUnterkategorieVerwalten;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

@Stateless
public class UnterkategorieVerwalten implements IUnterkategorieVerwalten {

    @Inject UnterkategorieDAO kategorieDAO;

    @Override
    public void unterkategorieAnlegen(UnterkategorieTO unterkategorieTO) {
        Unterkategorie kategorie = new Unterkategorie();
        kategorie = unterkategorieTO.toUnterkategorie();
        kategorieDAO.save(kategorie);
    }

    @Override
    public void unterkategorieUpdaten(UnterkategorieTO unterkategorieTO) {
        Unterkategorie kategorie = kategorieDAO.find(unterkategorieTO.getId());
        kategorie.setName(unterkategorieTO.getName());
        kategorie.setThema(unterkategorieTO.getThema().toThema());

        kategorieDAO.update(kategorie);
    }

    @Override
    public void unterkategorieLoeschen(long unterkategorieId) {
        kategorieDAO.delete(unterkategorieId);
    }

    @Override
    public Collection<UnterkategorieTO> ladeAlleUnterkategorien() {
        Collection<Unterkategorie> kategorienListe = kategorieDAO.findAll();
        Collection<UnterkategorieTO> kategorienTOListe = new ArrayList<UnterkategorieTO>();
        for (Unterkategorie u : kategorienListe) {
            kategorienTOListe.add(u.toUnterkategorieTO());
        }

        return kategorienTOListe;
    }

    @Override
    public UnterkategorieTO ladeUnterkategorie(long unterkategorieId) {
        Unterkategorie kategorie = new Unterkategorie();
        UnterkategorieTO kategorieTO = new UnterkategorieTO();

        kategorie = kategorieDAO.find(unterkategorieId);
        kategorieTO = kategorie.toUnterkategorieTO();
        return kategorieTO;
    }
}
