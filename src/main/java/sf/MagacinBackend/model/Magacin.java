package sf.MagacinBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Magacin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @ManyToOne
    private Preduzece preduzece;

    @JsonIgnore
    @OneToMany(mappedBy = "magacin")
    private List<Radnik> radnici=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "magacin")
    private List<RobnaKartica> robneKartice=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "magacin")
    private List<PrometniDokument> prometniDokumenti=new ArrayList<>();
}
