package sf.MagacinBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Mesto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Morate navesti naziv mesta")
    private String naziv;

    @NotBlank(message = "morate navesti ptt broj")
    private int ptt;

    @JsonIgnore
    @OneToMany(mappedBy = "mesto")
    private List<PoslovniPartner> poslovniPartneri = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "mesto")
    private List<Preduzece> preduzeca = new ArrayList<>();


    public PoslovniPartner addPoslovniPartner(PoslovniPartner poslovniPartner) {
        getPoslovniPartneri().add(poslovniPartner);
        poslovniPartner.setMesto(this);

        return poslovniPartner;
    }

    public PoslovniPartner removePoslovniPartner(PoslovniPartner poslovniPartner) {
        getPoslovniPartneri().remove(poslovniPartner);
        poslovniPartner.setMesto(null);

        return poslovniPartner;
    }

    public Preduzece addPreduzece(Preduzece preduzece) {
        getPreduzeca().add(preduzece);
        preduzece.setMesto(this);

        return preduzece;
    }

    public Preduzece removePreduzeca(Preduzece preduzeca) {
        getPreduzeca().remove(preduzeca);
        preduzeca.setMesto(null);

        return preduzeca;
    }
}
