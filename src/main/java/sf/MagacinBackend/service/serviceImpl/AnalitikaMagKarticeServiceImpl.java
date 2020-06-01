package sf.MagacinBackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.MagacinBackend.model.AnalitikaMagacinskeKartice;
import sf.MagacinBackend.model.RobnaKartica;
import sf.MagacinBackend.model.StavkaPrometnogDokumenta;
import sf.MagacinBackend.repository.AnalitikaMagKarticeRepository;
import sf.MagacinBackend.service.AnalitikaMagKarticeService;

import java.util.List;

@Service
public class AnalitikaMagKarticeServiceImpl implements AnalitikaMagKarticeService {
    @Autowired
    private AnalitikaMagKarticeRepository repository;


    @Override
    public AnalitikaMagacinskeKartice getOneByStavkaPrometnogDokumenta(StavkaPrometnogDokumenta stavkaPrometnogDokumenta) {
        return repository.findOneByStavkaDokumenta(stavkaPrometnogDokumenta);
    }

    @Override
    public List<AnalitikaMagacinskeKartice> getAllByRobnaKartica(RobnaKartica robnaKartica) {
        return repository.findAllByRobnaKartica(robnaKartica);
    }

    @Override
    public AnalitikaMagacinskeKartice insertKartica(AnalitikaMagacinskeKartice analitikaMagacinskeKartice) {
        return repository.save(analitikaMagacinskeKartice);
    }
}
