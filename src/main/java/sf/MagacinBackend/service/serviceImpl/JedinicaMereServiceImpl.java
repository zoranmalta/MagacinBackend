package sf.MagacinBackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.MagacinBackend.model.JedinicaMere;
import sf.MagacinBackend.repository.JedinicaMereRepository;
import sf.MagacinBackend.service.JedinicaMereService;

import java.util.List;

@Service
public class JedinicaMereServiceImpl implements JedinicaMereService {
    @Autowired
    private JedinicaMereRepository jedinicaMereRepository;

    @Override
    public List<JedinicaMere> getAll() {
        return jedinicaMereRepository.findAll();
    }
}
