package sf.MagacinBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PoslovnaGodina implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "boolean default false")
    private boolean zakljucena;

    @Column(name = "godina",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp godina;

    @Column
    protected boolean aktivna;

    @JsonIgnore
    @OneToMany(mappedBy = "poslovnaGodina")
    private List<RobnaKartica> listaRobnihKartica;

    @JsonIgnore
    @OneToMany(mappedBy = "poslovnaGodina")
    private List<PrometniDokument> listaPrometnihDokumenata;

}
