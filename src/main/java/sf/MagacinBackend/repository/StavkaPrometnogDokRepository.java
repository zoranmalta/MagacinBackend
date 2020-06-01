package sf.MagacinBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sf.MagacinBackend.model.PrometniDokument;
import sf.MagacinBackend.model.StavkaPrometnogDokumenta;

import java.util.List;

public interface StavkaPrometnogDokRepository extends JpaRepository<StavkaPrometnogDokumenta,Long> {
    List<StavkaPrometnogDokumenta> findAllByPrometniDokument(PrometniDokument prometniDokument);

}
