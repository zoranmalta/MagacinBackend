package sf.MagacinBackend.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AnalitikaMagacinskeKartice implements Serializable ,Comparable<AnalitikaMagacinskeKartice>{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int redniBroj;

    private Double cena;

    private Double kolicina;

    private Double vrednost;

    private TipPrometa tipPrometa;

    private Smer smer;

    @Column(name = "datum_formiranja",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp datumFormiranja;

    @ManyToOne
    private RobnaKartica robnaKartica;
    @ManyToOne
    private StavkaPrometnogDokumenta stavkaDokumenta;

    @Override
    public int compareTo(AnalitikaMagacinskeKartice o) {
        return this.getDatumFormiranja().getNanos()-o.getDatumFormiranja().getNanos();
    }
}
