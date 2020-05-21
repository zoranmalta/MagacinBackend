package sf.MagacinBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Roba implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sifra", unique = true, nullable = false)
    private String sifra;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @Column(name = "pakovanje", nullable = false)
    private Double pakovanje;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private GrupaRoba grupaRoba;

    @ManyToOne
    private JedinicaMere jedinicaMere;

    @JsonIgnore
    @OneToMany(mappedBy = "roba")
    private List<RobnaKartica> robneKartice=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "roba")
    private List<StavkaPrometnogDokumenta> stavkePrometnogDokumenta=new ArrayList<>();
}
