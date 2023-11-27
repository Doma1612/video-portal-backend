/* (C)2023 */
package de.videoportal.video.usecase.impl;

import de.videoportal.video.dao.ThemaDAO;
import de.videoportal.video.entity.ThemaTO;
import de.videoportal.video.entity.UnterkategorieTO;
import de.videoportal.video.entity.impl.Thema;
import de.videoportal.video.entity.impl.Unterkategorie;
import de.videoportal.video.usecase.IThemaVerwalten;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

@Stateless
public class ThemaVerwalten implements IThemaVerwalten {

    @Inject ThemaDAO themaDAO;

    @Override
    public void themaAnlegen(ThemaTO themaTO) {
        Thema thema = new Thema();
        thema = themaTO.toThema();
        themaDAO.save(thema);
    }

    @Override
    public void themaUpdaten(ThemaTO themaTO) {
        Thema thema = themaDAO.find(themaTO.getId());
        thema.setName(themaTO.getName());

        Collection<UnterkategorieTO> kategorienTO = themaTO.getUnterkategorien();
        Collection<Unterkategorie> kategorien = new ArrayList<Unterkategorie>();

        for (UnterkategorieTO k : kategorienTO) {
            kategorien.add(k.toUnterkategorie());
        }
        thema.setKategorien(kategorien);

        themaDAO.update(thema);
    }

    @Override
    public void themaLoeschen(long themaId) {
        Thema thema = themaDAO.find(themaId);
        themaDAO.delete(thema);
    }

    @Override
    public Collection<ThemaTO> ladeAlleThemen() {
        Collection<Thema> themaListe = themaDAO.findAll();
        Collection<ThemaTO> themaTOListe = new ArrayList<ThemaTO>();
        for (Thema t : themaListe) {
            themaTOListe.add(t.toThemaTO());
        }
        return themaTOListe;
    }

    @Override
    public ThemaTO ladeThema(long themaId) {
        Thema thema = new Thema();
        ThemaTO themaTO = new ThemaTO();

        thema = themaDAO.find(themaId);
        themaTO = thema.toThemaTO();

        return themaTO;
    }
}
