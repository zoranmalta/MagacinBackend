package sf.MagacinBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sf.MagacinBackend.model.StavkaPrometnogDokumenta;

public interface StavkaPrometnogDokRepository extends JpaRepository<StavkaPrometnogDokumenta,Long> {
}
