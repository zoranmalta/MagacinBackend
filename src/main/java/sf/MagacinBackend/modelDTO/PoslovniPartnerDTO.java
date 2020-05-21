package sf.MagacinBackend.modelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sf.MagacinBackend.model.TipPartnera;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PoslovniPartnerDTO {

    private Long id;
    private String naziv;
    private String adresa;
    private int pib;
    private PreduzeceDTO preduzece;
    private MestoDTO mesto;
    private TipPartnera tipPartnera;
}
