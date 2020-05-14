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
public class GrupaRoba implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String naziv;

    @ManyToOne
    private Preduzece preduzece;

    @JsonIgnore
    @OneToMany(targetEntity = Roba.class, mappedBy = "grupaRoba", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Roba> listaRobe= new ArrayList<>();

}
