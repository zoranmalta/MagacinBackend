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
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RobnaKartica implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //mozda treba redni broj kartice???

    private Double cena;

    private Double kolicinaPocetnogStanja;

    private Double kolicinaUlaza;
    private Double kolicinaIzlaza;

    private Double ukupnaKolicina;


    private Double vrednostPocetnogStanja;

    private Double vrednostUlaza;
    private Double vrednostIzlaza;
    
    private Double ukupnaVrednost;


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
    private List<AnalitikaMagacinskeKartice> analitike=new ArrayList<>();

    public boolean izracunajStanjeKartice(){
        this.setUkupnaKolicina(0.00);
        this.setKolicinaUlaza(0.00);
        this.setKolicinaIzlaza(0.00);

        this.setUkupnaVrednost(0.00);
        this.setVrednostUlaza(0.00);
        this.setVrednostIzlaza(0.00);

        List<AnalitikaMagacinskeKartice> list=new ArrayList<>(this.getAnalitike());
        //sortiram listu po datumnu
        list.sort((o1, o2) -> o1.getDatumFormiranja().compareTo(o2.getDatumFormiranja()));
        for(AnalitikaMagacinskeKartice a:list){
            if(a.getStavkaDokumenta()==null){
                continue;
            }
            //stavka ciji je dokument storniran ne ulazi u obracun
            if(a.getStavkaDokumenta().getPrometniDokument().getStatusDokumenta()==StatusDokumenta.Storniran){
                continue;
            }
            System.out.println("sortirana lista  : "+a.getDatumFormiranja());
            if(a.getStavkaDokumenta().getPrometniDokument()
                    .getTipPrometnogDokumenta() == TipPrometnogDokumenta.PRIJEMNICA){
                this.setKolicinaUlaza(this.getKolicinaUlaza() + a.getKolicina());
                this.setVrednostUlaza(this.getVrednostUlaza() + a.getVrednost());
            }
            if(a.getStavkaDokumenta().getPrometniDokument()
                    .getTipPrometnogDokumenta() == TipPrometnogDokumenta.OTPREMNICA){
                this.setKolicinaIzlaza(this.getKolicinaIzlaza() + a.getKolicina());
                this.setVrednostIzlaza(this.getVrednostIzlaza() + a.getVrednost());
            }
            if(a.getStavkaDokumenta().getPrometniDokument()
                    .getTipPrometnogDokumenta() == TipPrometnogDokumenta.MM &&
                a.getTipPrometa()==TipPrometa.OTPREMLJENO){
                this.setKolicinaUlaza(this.getKolicinaUlaza() - a.getKolicina());
                this.setVrednostUlaza(this.getVrednostUlaza() - a.getVrednost());
            }
            if(a.getStavkaDokumenta().getPrometniDokument()
                    .getTipPrometnogDokumenta() == TipPrometnogDokumenta.MM &&
                    a.getTipPrometa()==TipPrometa.DOBAVLJENO){
                this.setKolicinaUlaza(this.getKolicinaUlaza() + a.getKolicina());
                this.setVrednostUlaza(this.getVrednostUlaza() + a.getVrednost());
            }
            this.setUkupnaKolicina(this.getKolicinaPocetnogStanja()+this.getKolicinaUlaza()-this.getKolicinaIzlaza());
            if(this.getUkupnaKolicina()<0){
                return false;
            }
            this.setUkupnaVrednost(this.getVrednostPocetnogStanja()+this.getVrednostUlaza()-this.getVrednostIzlaza());
            this.setCena(a.getCena());
        }
        return true;
    }

    //racunamo stanje kartice do datuma odredjene analitike koju hocemo otpremimo
    public void izracunajStanjeKarticeNaDatum(Timestamp timestamp){
        this.setUkupnaKolicina(0.00);
        this.setKolicinaUlaza(0.00);
        this.setKolicinaIzlaza(0.00);

        this.setUkupnaVrednost(0.00);
        this.setVrednostUlaza(0.00);
        this.setVrednostIzlaza(0.00);
        List<AnalitikaMagacinskeKartice> list=new ArrayList<>(this.getAnalitike());
        Collections.sort(list, (d1, d2) -> {
            return d2.getDatumFormiranja().getNanos()- d1.getDatumFormiranja().getNanos();
        });
        for(AnalitikaMagacinskeKartice a:list){
            if(a.getDatumFormiranja().after(timestamp)){
                continue;
            }
            if(a.getStavkaDokumenta().getPrometniDokument()
                    .getTipPrometnogDokumenta() == TipPrometnogDokumenta.PRIJEMNICA){
                this.setKolicinaUlaza(this.getKolicinaUlaza() + a.getKolicina());
                this.setVrednostUlaza(this.getVrednostUlaza() + a.getVrednost());
            }
            if(a.getStavkaDokumenta().getPrometniDokument()
                    .getTipPrometnogDokumenta() == TipPrometnogDokumenta.OTPREMNICA){
                this.setKolicinaIzlaza(this.getKolicinaIzlaza() + a.getKolicina());
                this.setVrednostIzlaza(this.getVrednostIzlaza() + a.getVrednost());
            }
            if(a.getStavkaDokumenta().getPrometniDokument()
                    .getTipPrometnogDokumenta() == TipPrometnogDokumenta.MM &&
                    a.getTipPrometa()==TipPrometa.OTPREMLJENO){
                this.setKolicinaUlaza(this.getKolicinaUlaza() - a.getKolicina());
                this.setVrednostUlaza(this.getVrednostUlaza() - a.getVrednost());
            }
            if(a.getStavkaDokumenta().getPrometniDokument()
                    .getTipPrometnogDokumenta() == TipPrometnogDokumenta.MM &&
                    a.getTipPrometa()==TipPrometa.DOBAVLJENO){
                this.setKolicinaUlaza(this.getKolicinaUlaza() + a.getKolicina());
                this.setVrednostUlaza(this.getVrednostUlaza() + a.getVrednost());
            }
            this.setUkupnaKolicina(this.getKolicinaPocetnogStanja()+this.getKolicinaUlaza()-this.getKolicinaIzlaza());
            this.setUkupnaVrednost(this.getVrednostPocetnogStanja()+this.getVrednostUlaza()-this.getVrednostIzlaza());
        }
    }
}
