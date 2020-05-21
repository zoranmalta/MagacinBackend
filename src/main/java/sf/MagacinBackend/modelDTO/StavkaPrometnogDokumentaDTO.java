package sf.MagacinBackend.modelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sf.MagacinBackend.model.PrometniDokument;
import sf.MagacinBackend.model.Roba;

import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StavkaPrometnogDokumentaDTO {
    private Long id;
    private Double cena;
    private Double kolicina;
    private Double vrednost;
    private RobaDTO roba;
    private PrometniDokumentDTO prometniDokument;
}
