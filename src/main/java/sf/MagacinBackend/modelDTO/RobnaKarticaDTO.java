package sf.MagacinBackend.modelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sf.MagacinBackend.modelDTO.MagacinDTO;
import sf.MagacinBackend.modelDTO.PoslovnaGodinaDTO;
import sf.MagacinBackend.modelDTO.RobaDTO;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class RobnaKarticaDTO {
    private Long id;

    private Double cena;

    private Double kolicinaUlaza;
    private Double vrednostUlaza;

    private Double kolicinaPocetnogStanja;
    private Double vrednostPocetnogStanja;

    private Double ukupnaKolicina;
    private Double ukupnaVrednost;

    private Double vrednostIzlaza;
    private Double kolicinaIzlaza;

    private MagacinDTO magacin;
    private RobaDTO roba;
    private PoslovnaGodinaDTO poslovnaGodina;

}
