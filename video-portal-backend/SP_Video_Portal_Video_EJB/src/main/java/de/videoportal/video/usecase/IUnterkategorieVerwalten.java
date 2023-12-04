/* (C)2023 */
package de.videoportal.video.usecase;

import de.videoportal.video.entity.UnterkategorieTO;
import jakarta.ejb.Local;
import java.util.Collection;

@Local
public interface IUnterkategorieVerwalten {

    public void unterkategorieAnlegen(UnterkategorieTO unterkategorieTO);

    public void unterkategorieUpdaten(UnterkategorieTO unterkategorieTO);

    public void unterkategorieLoeschen(long unterkategorieId);

    public Collection<UnterkategorieTO> ladeAlleUnterkategorien();

    public UnterkategorieTO ladeUnterkategorie(long unterkategorieId);
}
