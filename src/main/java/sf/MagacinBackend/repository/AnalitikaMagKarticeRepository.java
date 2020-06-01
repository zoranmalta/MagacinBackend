package sf.MagacinBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sf.MagacinBackend.model.AnalitikaMagacinskeKartice;
import sf.MagacinBackend.model.RobnaKartica;
import sf.MagacinBackend.model.StavkaPrometnogDokumenta;

import java.util.List;

public interface AnalitikaMagKarticeRepository extends JpaRepository<AnalitikaMagacinskeKartice,Long> {

    List<AnalitikaMagacinskeKartice> findAllByRobnaKartica(RobnaKartica robnaKartica);
    AnalitikaMagacinskeKartice findOneByStavkaDokumenta(StavkaPrometnogDokumenta stavkaPrometnogDokumenta);

}
