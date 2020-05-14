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
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RobnaKartica implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //mozda treba redni broj kartice???

    private Double cena;

    private Double kolicinaUlaza;
    private Double vrednostUlaza;

    private Double kolicinaPocetnogStanja;
    private Double vrednostPocetnogStanja;

    private Double ukupnaKolicina;
    private Double ukupnaVrednost;

    private Double vrednostIzlaza;
    private Double kolicinaIzlaza;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Magacin magacin;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Roba roba;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private PoslovnaGodina poslovnaGodina;

    @JsonIgnore
    @OneToMany(mappedBy = "robnaKartica")
    private List<AnalitikaMagacinskeKartice> analitike;

}
