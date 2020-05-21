package sf.MagacinBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Preduzece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Mesto mesto;

    @NotBlank
    private String naziv;

    @NotBlank(message = "morate popuniti pib")
    @Size(min=8, max=8, message = "PIB mora sadrzati tacno 8 cifara")
    private int pib;

    @NotBlank
    private String adresa;

    @JsonIgnore
    @OneToMany(targetEntity = Radnik.class, mappedBy = "preduzece")
    private List<Radnik> radnici=new ArrayList<>();

    @JsonIgnore
    @OneToMany(targetEntity = GrupaRoba.class, mappedBy = "preduzece")
    private List<GrupaRoba> grupaRoba=new ArrayList<>();

    @JsonIgnore
    @OneToMany(targetEntity = Magacin.class, mappedBy = "preduzece")
    private List<Magacin> magacini=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "preduzece")
    private List<PoslovniPartner> poslovniPartneri=new ArrayList<>();


}
