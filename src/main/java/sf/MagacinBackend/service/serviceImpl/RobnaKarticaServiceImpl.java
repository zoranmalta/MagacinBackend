package sf.MagacinBackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.MagacinBackend.model.Magacin;
import sf.MagacinBackend.model.PoslovnaGodina;
import sf.MagacinBackend.model.Roba;
import sf.MagacinBackend.model.RobnaKartica;
import sf.MagacinBackend.repository.RobnaKarticaRepository;
import sf.MagacinBackend.service.RobnaKarticaService;

import java.util.List;

@Service
public class RobnaKarticaServiceImpl implements RobnaKarticaService {
    @Autowired
    private RobnaKarticaRepository robnaKarticaRepository;

    @Override
    public void insert(RobnaKartica robnaKartica) {
        robnaKarticaRepository.save(robnaKartica);
    }

    @Override
    public RobnaKartica getOneByRoba(Roba roba) {
        return robnaKarticaRepository.findOneByRoba(roba);
    }

    @Override
    public List<RobnaKartica> getAllByMagacin(Magacin magacin) {
        return robnaKarticaRepository.findAllByMagacin(magacin);
    }
    @Override
    public void insertAll(Magacin magacin,List<Roba> robaList,PoslovnaGodina poslovnaGodina){
        for (Roba r : robaList) {
            RobnaKartica rk = new RobnaKartica();
            rk.setRoba(r);
            rk.setMagacin(magacin);
            rk.setPoslovnaGodina(poslovnaGodina);
            rk.setCena(0.00);
            rk.setVrednostUlaza(0.00);
            rk.setVrednostPocetnogStanja(0.00);
            rk.setVrednostIzlaza(0.00);
            rk.setUkupnaVrednost(0.00);
            rk.setUkupnaKolicina(0.00);
            rk.setKolicinaPocetnogStanja(0.00);
            rk.setKolicinaUlaza(0.00);
            rk.setKolicinaIzlaza(0.00);
            insert(rk);
        }
    }

    @Override
    public RobnaKartica getOneByRobaAndMagacinAndPoslovnaGodina(Roba roba, Magacin magacin, PoslovnaGodina poslovnaGodina) {
        return robnaKarticaRepository.findOneByRobaAndMagacinAndPoslovnaGodina(roba,magacin,poslovnaGodina);
    }
}
