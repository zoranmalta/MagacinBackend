package sf.MagacinBackend.service;

import sf.MagacinBackend.model.AnalitikaMagacinskeKartice;
import sf.MagacinBackend.model.RobnaKartica;
import sf.MagacinBackend.model.StavkaPrometnogDokumenta;

import java.util.List;

public interface AnalitikaMagKarticeService {
    AnalitikaMagacinskeKartice getOneByStavkaPrometnogDokumenta(StavkaPrometnogDokumenta stavkaPrometnogDokumenta);
    List<AnalitikaMagacinskeKartice> getAllByRobnaKartica(RobnaKartica robnaKartica);
    AnalitikaMagacinskeKartice insertKartica(AnalitikaMagacinskeKartice analitikaMagacinskeKartice);
}
