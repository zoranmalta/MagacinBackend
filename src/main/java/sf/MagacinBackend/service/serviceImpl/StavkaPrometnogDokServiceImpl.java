package sf.MagacinBackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.MagacinBackend.model.StavkaPrometnogDokumenta;
import sf.MagacinBackend.repository.StavkaPrometnogDokRepository;
import sf.MagacinBackend.service.StavkaPrometnogDokService;

import java.util.List;

@Service
public class StavkaPrometnogDokServiceImpl implements StavkaPrometnogDokService {
    @Autowired
    private StavkaPrometnogDokRepository repository;


    @Override
    public List<StavkaPrometnogDokumenta> insertAll(List<StavkaPrometnogDokumenta> stavke) {
        return repository.saveAll(stavke);
    }

    @Override
    public void deleteAll(List<StavkaPrometnogDokumenta> stavke) {
        repository.deleteAll(stavke);
    }
}
