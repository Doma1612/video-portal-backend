/* (C)2023 */
package de.videoportal.video.usecase;

import de.videoportal.video.entity.ThemaTO;
import jakarta.ejb.Local;
import java.util.Collection;

@Local
public interface IThemaVerwalten {

    public void themaAnlegen(ThemaTO themaTO);

    public void themaUpdaten(ThemaTO themaTO);

    public void themaLoeschen(long themaId);

    public Collection<ThemaTO> ladeAlleThemen();

    public ThemaTO ladeThema(long themaId);
}
