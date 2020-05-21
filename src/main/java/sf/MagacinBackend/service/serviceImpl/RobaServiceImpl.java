package sf.MagacinBackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sf.MagacinBackend.model.Roba;
import sf.MagacinBackend.repository.RobaRepository;
import sf.MagacinBackend.service.RobaService;

import java.util.List;

@Service
public class RobaServiceImpl implements RobaService {
    @Autowired
    private RobaRepository robaRepository;


    @Override
    public List<Roba> getAll() {
        return robaRepository.findAll();
    }

    @Override
    @Transactional
    public Roba insertRoba(Roba roba) {
        return robaRepository.save(roba);
    }
}
