package sf.MagacinBackend.model;

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
public class JedinicaMere implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJedMere;

    @NotBlank(message = "Morate navesti naziv jedinice mere")
    private String naziv;

    @OneToMany(mappedBy = "jedinicaMere")
    private List<Roba> listaRobe = new ArrayList<>();
}
