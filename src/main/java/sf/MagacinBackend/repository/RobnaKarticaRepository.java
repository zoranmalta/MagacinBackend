package sf.MagacinBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sf.MagacinBackend.model.Magacin;
import sf.MagacinBackend.model.PoslovnaGodina;
import sf.MagacinBackend.model.Roba;
import sf.MagacinBackend.model.RobnaKartica;

import java.util.List;

public interface RobnaKarticaRepository extends JpaRepository<RobnaKartica,Long> {
    RobnaKartica findOneByRoba(Roba roba);
    List<RobnaKartica> findAllByMagacin(Magacin magacin);
    RobnaKartica findOneByRobaAndMagacinAndPoslovnaGodina(Roba roba, Magacin magacin, PoslovnaGodina poslovnaGodina);
}
