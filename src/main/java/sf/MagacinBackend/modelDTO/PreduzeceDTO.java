package sf.MagacinBackend.modelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PreduzeceDTO {

    private Long id;
    private String naziv;
    private String adresa;
    private int pib;
    private MestoDTO mesto;
}
