package sf.MagacinBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sf.MagacinBackend.model.PoslovniPartner;

public interface PoslovniPartnerRepository extends JpaRepository<PoslovniPartner,Long> {
}
