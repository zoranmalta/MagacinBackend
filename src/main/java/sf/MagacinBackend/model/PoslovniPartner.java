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
public class PoslovniPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Mesto mesto;

    @ManyToOne
    private Preduzece preduzece;

    @NotBlank
    private TipPartnera tipPartnera;

    @NotBlank(message = "Morate navesti naziv poslovnog partnera")
    private String naziv;

    @NotBlank(message = "Morate navesti adresu poslovnog partnera")
    private String adresa;

    @NotBlank(message = "Morate navesti PIB poslovnog partnera")
    @Size(min=8, max=8, message = "PIB mora sadrzati tacno 8 cifara")
    private int pib;

    @JsonIgnore
    @OneToMany(mappedBy = "poslovniPartner")
    private List<PrometniDokument> prometniDokumenti = new ArrayList<>();


}
