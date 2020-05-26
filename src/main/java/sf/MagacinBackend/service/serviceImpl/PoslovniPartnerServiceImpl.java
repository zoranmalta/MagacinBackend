package sf.MagacinBackend.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.MagacinBackend.model.PoslovniPartner;
import sf.MagacinBackend.repository.PoslovniPartnerRepository;
import sf.MagacinBackend.service.PoslovniPartnerService;

import java.util.List;

@Service
public class PoslovniPartnerServiceImpl implements PoslovniPartnerService {
    @Autowired
    private PoslovniPartnerRepository poslovniPartnerRepository;

    @Override
    public List<PoslovniPartner> getAll() {
        return poslovniPartnerRepository.findAll();
    }
}
