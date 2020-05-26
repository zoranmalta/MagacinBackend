package sf.MagacinBackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.MagacinBackend.model.Magacin;
import sf.MagacinBackend.repository.MagacinRepository;
import sf.MagacinBackend.service.MagacinService;

import java.util.List;

@Service
public class MagacinServiceImpl implements MagacinService {
    @Autowired
    private MagacinRepository magacinRepository;

    @Override
    public List<Magacin> getAll() {
        return magacinRepository.findAll();
    }
}
