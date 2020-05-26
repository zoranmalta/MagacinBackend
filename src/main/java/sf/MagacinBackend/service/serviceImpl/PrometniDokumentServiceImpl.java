package sf.MagacinBackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sf.MagacinBackend.model.PrometniDokument;
import sf.MagacinBackend.model.Roba;
import sf.MagacinBackend.model.RobnaKartica;
import sf.MagacinBackend.model.StavkaPrometnogDokumenta;
import sf.MagacinBackend.repository.PrometniDokumentRepository;
import sf.MagacinBackend.service.PrometniDokumentService;
import sf.MagacinBackend.service.RobnaKarticaService;
import sf.MagacinBackend.service.StavkaPrometnogDokService;

import java.util.List;

@Service
public class PrometniDokumentServiceImpl implements PrometniDokumentService {
    @Autowired
    private PrometniDokumentRepository repository;
    @Autowired
    private RobnaKarticaService robnaKarticaService;
    @Autowired
    private StavkaPrometnogDokService stavkaPrometnogDokService;

    @Override
    public List<PrometniDokument> getAll() {
        return repository.findAll();
    }

    @Transactional
    public void prometniDokumentStavkeTransaction(PrometniDokument p
            , List<StavkaPrometnogDokumenta> list){
        p=repository.save(p);
        for (StavkaPrometnogDokumenta s : list) {
            s.setPrometniDokument(p);
        }
        list=stavkaPrometnogDokService.insertAll(list);
    }

    @Override
    public PrometniDokument insert(PrometniDokument prometniDokument) {
        return repository.save(prometniDokument);
    }
}
