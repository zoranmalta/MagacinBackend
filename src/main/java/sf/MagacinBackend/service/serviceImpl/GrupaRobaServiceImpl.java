package sf.MagacinBackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.MagacinBackend.model.GrupaRoba;
import sf.MagacinBackend.repository.GrupaRobaRepository;
import sf.MagacinBackend.service.GrupaRobaService;

import java.util.List;

@Service
public class GrupaRobaServiceImpl implements GrupaRobaService {
    @Autowired
    private GrupaRobaRepository grupaRobaRepository;

    @Override
    public List<GrupaRoba> getAll() {
        return grupaRobaRepository.findAll();
    }
}
