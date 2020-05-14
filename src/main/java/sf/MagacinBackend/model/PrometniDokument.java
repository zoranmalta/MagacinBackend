package sf.MagacinBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PrometniDokument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int redniBroj;

    @Column(name = "datum_formiranja",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp datumFormiranja;

    @Column(name = "datum_knjizenja")
    private Timestamp datumKnjizenja;

    private TipPrometnogDokumenta tipPrometnogDokumenta;

    private StatusDokumenta statusDokumenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private PoslovniPartner poslovniPartner;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Magacin magacin;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Magacin magacin2;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private PoslovnaGodina poslovnaGodina;

    @JsonIgnore
    @OneToMany(mappedBy = "prometniDokument" ,orphanRemoval = false, fetch = FetchType.LAZY,targetEntity = StavkaPrometnogDokumenta.class)
    private List<StavkaPrometnogDokumenta> listaStavki=new ArrayList<>();



}
