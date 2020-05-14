package sf.MagacinBackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StavkaPrometnogDokumenta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cena;

    private Double kolicina;

    private Double vrednost;

    @ManyToOne
    private Roba roba;

    @ManyToOne
    private PrometniDokument prometniDokument;

    //mozda treba jos lista analiticke kartice ???

}
