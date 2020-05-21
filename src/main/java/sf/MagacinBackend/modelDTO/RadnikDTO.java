package sf.MagacinBackend.modelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RadnikDTO {

    private Long id;
    private String ime;
    private String prezime;
    private PreduzeceDTO preduzece;
    private MagacinDTO magacin;
}
