package sf.MagacinBackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sf.MagacinBackend.model.*;
import sf.MagacinBackend.repository.PrometniDokumentRepository;
import sf.MagacinBackend.service.AnalitikaMagKarticeService;
import sf.MagacinBackend.service.PrometniDokumentService;
import sf.MagacinBackend.service.RobnaKarticaService;
import sf.MagacinBackend.service.StavkaPrometnogDokService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrometniDokumentServiceImpl implements PrometniDokumentService {
    @Autowired
    private PrometniDokumentRepository repository;
    @Autowired
    private RobnaKarticaService robnaKarticaService;
    @Autowired
    private StavkaPrometnogDokService stavkaPrometnogDokService;
    @Autowired
    private AnalitikaMagKarticeService analitikaMagKarticeService;

    @Override
    public List<PrometniDokument> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void insertPrijemnicaTransaction(PrometniDokument p
            , List<StavkaPrometnogDokumenta> list){
        // lista koja prima robu za koju ne postoji karticaa i pravimo sve kartice
        // za robu iz odredjenog magacina
        List<Roba> robaList=new ArrayList<>();
        if(p.getTipPrometnogDokumenta()==TipPrometnogDokumenta.PRIJEMNICA){
            for (StavkaPrometnogDokumenta s:list){
                RobnaKartica r=robnaKarticaService.getOneByRobaAndMagacinAndPoslovnaGodina
                        (s.getRoba(),p.getMagacin(),p.getPoslovnaGodina());
                if(r==null){
                    robaList.add(s.getRoba());
                }
            }
        }
        //formiramo nove robne kartice ako ne postoje
        if(robaList.size()>0){
            robnaKarticaService.insertAll(p.getMagacin(),robaList,p.getPoslovnaGodina());
        }
        // tek sad insertujemo prometni dokument i kasnije sve stavke dokumenta u transakciji
        p=repository.save(p);
        for (StavkaPrometnogDokumenta s : list) {
            s.setPrometniDokument(p);
        }
        list=stavkaPrometnogDokService.insertAll(list);
        //formiram analiticke kartice za svaku stavku sa datumom unosa robe
        for(StavkaPrometnogDokumenta s:list){
            RobnaKartica r=robnaKarticaService.getOneByRobaAndMagacinAndPoslovnaGodina
                    (s.getRoba(),s.getPrometniDokument().getMagacin(),s.getPrometniDokument().getPoslovnaGodina());
            AnalitikaMagacinskeKartice a=new AnalitikaMagacinskeKartice();
            //smer i tip za prijemnicu i MM su isti Ulaz i Dobavljeno
            a.setSmer(Smer.ULAZ);
            a.setTipPrometa(TipPrometa.DOBAVLJENO);
            a.setRedniBroj(1);
            a.setStavkaDokumenta(s);
            a.setDatumFormiranja(s.getPrometniDokument().getDatumFormiranja());
            a.setVrednost(s.getVrednost());
            a.setCena(s.getCena());
            a.setKolicina(s.getKolicina());
            //setovanje stanja u bazi
            r.getAnalitike().add(a);
            r.izracunajStanjeKartice();
            a.setRobnaKartica(r);
            a=analitikaMagKarticeService.insertKartica(a);
        }
    }

    @Override
    @Transactional
    public void insertOtpremnicaTransaction(PrometniDokument p, List<StavkaPrometnogDokumenta> list) {
        // posle provere insertujemo prometni dokument i kasnije sve stavke dokumenta u transakciji
        p=repository.save(p);
        for (StavkaPrometnogDokumenta s : list) {
            s.setPrometniDokument(p);
        }
        list=stavkaPrometnogDokService.insertAll(list);
        //formiram analiticke kartice za svaku stavku sa datumom unosa robe
        for(StavkaPrometnogDokumenta s:list){
            RobnaKartica r=robnaKarticaService.getOneByRobaAndMagacinAndPoslovnaGodina
                    (s.getRoba(),s.getPrometniDokument().getMagacin(),s.getPrometniDokument().getPoslovnaGodina());
            AnalitikaMagacinskeKartice a=new AnalitikaMagacinskeKartice();
            a.setRedniBroj(1);
            //smer i tip za Otpremnicu i MM nisu isti (izlaz i Otpremljeno , Ulaz i Otpremljeno
            if(p.getTipPrometnogDokumenta()==TipPrometnogDokumenta.OTPREMNICA){
                a.setSmer(Smer.IZLAZ);
                a.setTipPrometa(TipPrometa.OTPREMLJENO);
            }
            a.setStavkaDokumenta(s);
            a.setDatumFormiranja(s.getPrometniDokument().getDatumFormiranja());
            a.setVrednost(s.getVrednost());
            a.setCena(s.getCena());
            a.setKolicina(s.getKolicina());
            a.setRobnaKartica(r);
            a=analitikaMagKarticeService.insertKartica(a);
        }
    }
    @Override
    @Transactional
    public void insertMMTransaction(PrometniDokument p, List<StavkaPrometnogDokumenta> list){
        // posle provere insertujemo prometni dokument i kasnije sve stavke dokumenta u transakciji
        p=repository.save(p);
        for (StavkaPrometnogDokumenta s : list) {
            s.setPrometniDokument(p);
        }
        list=stavkaPrometnogDokService.insertAll(list);
        //formiram analiticke kartice za svaku stavku sa datumom unosa robe
        for(StavkaPrometnogDokumenta s:list){
            RobnaKartica r=robnaKarticaService.getOneByRobaAndMagacinAndPoslovnaGodina
                    (s.getRoba(),s.getPrometniDokument().getMagacin(),s.getPrometniDokument().getPoslovnaGodina());
            AnalitikaMagacinskeKartice a=new AnalitikaMagacinskeKartice();
            a.setRedniBroj(1);
            //smer i tip za Otpremnicu i MM nisu isti (izlaz i Otpremljeno , Ulaz i Otpremljeno
            a.setSmer(Smer.ULAZ);
            a.setTipPrometa(TipPrometa.OTPREMLJENO);
            a.setStavkaDokumenta(s);
            a.setDatumFormiranja(s.getPrometniDokument().getDatumFormiranja());
            a.setVrednost(s.getVrednost());
            a.setCena(s.getCena());
            a.setKolicina(s.getKolicina());
            a.setRobnaKartica(r);
            a=analitikaMagKarticeService.insertKartica(a);
        }
        // lista koja prima robu za koju ne postoji karticaa i pravimo sve kartice
        // za robu za odredjeni magacina 2!!
        List<Roba> robaList=new ArrayList<>();
        for (StavkaPrometnogDokumenta s:list){
            RobnaKartica r=robnaKarticaService.getOneByRobaAndMagacinAndPoslovnaGodina
                    (s.getRoba(),p.getMagacin2(),p.getPoslovnaGodina());
            if(r==null){
                robaList.add(s.getRoba());
            }
        }
        //formiramo nove robne kartice ako ne postoje u magacinu 2
        if(robaList.size()>0){
            robnaKarticaService.insertAll(p.getMagacin2(),robaList,p.getPoslovnaGodina());
        }
        //formiram analiticke kartice za svaku stavku sa datumom unosa robe za magacin 2
        for(StavkaPrometnogDokumenta s:list){
            RobnaKartica r=robnaKarticaService.getOneByRobaAndMagacinAndPoslovnaGodina
                    (s.getRoba(),s.getPrometniDokument().getMagacin2(),s.getPrometniDokument().getPoslovnaGodina());
            AnalitikaMagacinskeKartice a=new AnalitikaMagacinskeKartice();
            //smer i tip za prijemnicu i MM su isti Ulaz i Dobavljeno
            a.setSmer(Smer.ULAZ);
            a.setTipPrometa(TipPrometa.DOBAVLJENO);
            a.setRedniBroj(1);
            a.setStavkaDokumenta(s);
            a.setDatumFormiranja(s.getPrometniDokument().getDatumFormiranja());
            a.setVrednost(s.getVrednost());
            a.setCena(s.getCena());
            a.setKolicina(s.getKolicina());
            //setovanje stanja kartice u bazi
            r.getAnalitike().add(a);
            r.izracunajStanjeKartice();
            a.setRobnaKartica(r);
            a=analitikaMagKarticeService.insertKartica(a);
        }
    };

    @Override
    @Transactional
    public void knjizenjeIliStorno(PrometniDokument prometniDokument) {
        //update prometnogDokumneta
        prometniDokument=insert(prometniDokument);
    }

    //provera kartice za sve slucajeve inserta (Otpremnice i MM)!!!
    @Override
    public boolean proveriRobneKarticeZaInsert(PrometniDokument p, List<StavkaPrometnogDokumenta> list) {
        boolean check=false;
        for (StavkaPrometnogDokumenta s:list){
            RobnaKartica r=robnaKarticaService.getOneByRobaAndMagacinAndPoslovnaGodina
                    (s.getRoba(),p.getMagacin(),p.getPoslovnaGodina());
            if(r==null){
                //ne postoji kartica
                return false;
            }
            s.setPrometniDokument(p);
            //formiram analitiku koju planiram da unesem u bazu i testiram karticu za
            // sve analitike zajedno kao da je u bazi
            AnalitikaMagacinskeKartice a=new AnalitikaMagacinskeKartice();
            a.setRedniBroj(1);

            if(p.getTipPrometnogDokumenta()==TipPrometnogDokumenta.OTPREMNICA){
                a.setSmer(Smer.IZLAZ);
                a.setTipPrometa(TipPrometa.OTPREMLJENO);
            }
            if(p.getTipPrometnogDokumenta()==TipPrometnogDokumenta.PRIJEMNICA){
                a.setSmer(Smer.ULAZ);
                a.setTipPrometa(TipPrometa.DOBAVLJENO);
            }
            if(p.getTipPrometnogDokumenta()==TipPrometnogDokumenta.MM){
                a.setSmer(Smer.ULAZ);
                a.setTipPrometa(TipPrometa.OTPREMLJENO);
            }
            a.setStavkaDokumenta(s);
            a.setDatumFormiranja(p.getDatumFormiranja());
            a.setVrednost(s.getVrednost());
            a.setCena(s.getCena());
            a.setKolicina(s.getKolicina());
            a.setRobnaKartica(r);
            r.getAnalitike().add(a);

            if(r!=null){
                check=r.izracunajStanjeKartice();
                System.out.println("Ukupna kolicina robe po kartici : "+r.getUkupnaKolicina());
            }
        }
        return check;
    }
    @Override
    public boolean proveriRobneKarticeZaStorno(PrometniDokument p, List<StavkaPrometnogDokumenta> list){
        boolean check=false;
        for (StavkaPrometnogDokumenta s:list){
            RobnaKartica r=null;

            if(p.getTipPrometnogDokumenta()==TipPrometnogDokumenta.PRIJEMNICA||
                    p.getTipPrometnogDokumenta()==TipPrometnogDokumenta.OTPREMNICA){
                r=robnaKarticaService.getOneByRobaAndMagacinAndPoslovnaGodina(s.getRoba()
                        ,p.getMagacin(),p.getPoslovnaGodina());
            }
            if(p.getTipPrometnogDokumenta()==TipPrometnogDokumenta.MM){
                r=robnaKarticaService.getOneByRobaAndMagacinAndPoslovnaGodina(s.getRoba()
                        ,p.getMagacin2(),p.getPoslovnaGodina());
            }

            if(r==null){
                //ne postoji kartica
                return false;
            }else{
                //filtriram analitiku koju zelim da storniram i takvu karticu proveravam bez te analitike
                //pocetnu analitiku kojoj je stavkaDokumenta null vracam isto
                r.setAnalitike(r.getAnalitike().stream().filter(analitikaMagacinskeKartice ->{
                    if(analitikaMagacinskeKartice.getStavkaDokumenta()!=null){
                        return analitikaMagacinskeKartice.getStavkaDokumenta().getId()!=s.getId();
                    }else {
                        return true;
                    }
                }
                       ).collect(Collectors.toList()));
                check=r.izracunajStanjeKartice();
                if(r.getUkupnaKolicina()<0){
                    return false;
                }
                System.out.println("Ukupna kolicina robe po kartici : "+r.getUkupnaKolicina());
            }
        }
        return check;
    }

    @Override
    public PrometniDokument insert(PrometniDokument prometniDokument) {
        return repository.save(prometniDokument);
    }
}
