package sf.MagacinBackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.MagacinBackend.model.PoslovnaGodina;
import sf.MagacinBackend.repository.PoslovnaGodinaRepository;
import sf.MagacinBackend.service.PoslovnaGodinaService;

import java.sql.Timestamp;

@Service
public class PoslovnaGodinaServiceImpl implements PoslovnaGodinaService {
    @Autowired
    private PoslovnaGodinaRepository repository;

    @Override
    public PoslovnaGodina findOneByDate(Timestamp timestamp) {
        return repository.fetchGodinaByDate(timestamp);
    }
}
