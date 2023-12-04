/* (C)2023 */
package de.videoportal.video.usecase;

import de.videoportal.video.entity.VideoTO;
import jakarta.ejb.Local;

@Local
public interface IAufrufeZaehlen {

    public void aufrufeVideoUpdaten(VideoTO videoTO);

    public void aufrufeVideoLaden(VideoTO videoTO);
}
