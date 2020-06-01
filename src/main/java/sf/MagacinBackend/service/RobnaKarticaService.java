package sf.MagacinBackend.service;

import sf.MagacinBackend.model.Magacin;
import sf.MagacinBackend.model.PoslovnaGodina;
import sf.MagacinBackend.model.Roba;
import sf.MagacinBackend.model.RobnaKartica;

import java.util.List;

public interface RobnaKarticaService  {
    void insert(RobnaKartica robnaKartica);
    RobnaKartica getOneByRoba(Roba roba);
    List<RobnaKartica> getAllByMagacin(Magacin magacin);
    void insertAll(Magacin magacin,List<Roba> robaList,PoslovnaGodina poslovnaGodina);
    RobnaKartica getOneByRobaAndMagacinAndPoslovnaGodina(Roba roba, Magacin magacin, PoslovnaGodina poslovnaGodina);
}
