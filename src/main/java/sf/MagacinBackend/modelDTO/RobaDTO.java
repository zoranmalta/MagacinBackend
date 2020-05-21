package sf.MagacinBackend.modelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RobaDTO {

    private Long id;
    private String sifra;
    private String naziv;
    private Double pakovanje;
    private JedinicaMereDTO jedinicaMere;
    private GrupaRobaDTO grupaRoba;
}
