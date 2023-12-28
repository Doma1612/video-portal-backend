/* (C)2023 */
package de.videoportal.video.usecase;

import de.videoportal.video.entity.impl.Video;
import jakarta.ejb.Local;

@Local
public interface IKonvertiereVideo {

    public boolean konvertiereUndSpeichereVideo(byte[] videoBytes, Video v, String dateiEndung);

    public boolean empfangeVideoDaten(
            String dateiEndung,
            String titel,
            String thema,
            String beschreibung,
            String stichwoerter,
            String unterkategorien,
            byte[] videoBytes);

    public void legeOrdnerStrukturAn(String path);
}
