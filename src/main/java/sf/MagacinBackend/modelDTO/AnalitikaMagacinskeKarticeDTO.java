package sf.MagacinBackend.modelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sf.MagacinBackend.model.RobnaKartica;
import sf.MagacinBackend.model.Smer;
import sf.MagacinBackend.model.TipPrometa;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AnalitikaMagacinskeKarticeDTO {

    private Long id;

    private int redniBroj;

    private Double cena;

    private Double kolicina;

    private Double vrednost;

    private TipPrometa tipPrometa;

    private Smer smer;

    private Timestamp datumFormiranja;

    private RobnaKarticaDTO robnaKartica;

    private StavkaPrometnogDokumentaDTO stavkaDokumenta;

}
