package sf.MagacinBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "stavkaDokumenta")
    private List<AnalitikaMagacinskeKartice> listaAnalitika=new ArrayList<>();

}
