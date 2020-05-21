package sf.MagacinBackend.modelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MagacinDTO {
    private Long id;
    private String naziv;
    private PreduzeceDTO preduzece;
}
