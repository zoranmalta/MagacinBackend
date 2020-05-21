package sf.MagacinBackend.modelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sf.MagacinBackend.model.StatusDokumenta;
import sf.MagacinBackend.model.TipPrometnogDokumenta;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PrometniDokumentDTO {
    private Long id;
    private String redniBroj;
    private Timestamp datumFormiranja;
    private Timestamp datumKnjizenja;
    private TipPrometnogDokumenta tipPrometnogDokumenta;
    private StatusDokumenta statusDokumenta;
    private MagacinDTO magacin;
    private MagacinDTO magacin2;
    private PoslovniPartnerDTO poslovniPartner;
    private PoslovnaGodinaDTO poslovnaGodina;
}
