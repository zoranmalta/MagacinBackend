package sf.MagacinBackend.modelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PoslovnaGodinaDTO {
    private Long id;

    private boolean zakljucena;

    private Timestamp godinaStart;

    private Timestamp godinaEnd;

    protected boolean aktivna;
}
