package sf.MagacinBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sf.MagacinBackend.model.PoslovnaGodina;

import java.sql.Timestamp;

public interface PoslovnaGodinaRepository extends JpaRepository<PoslovnaGodina,Long> {
    @Query(value = "SELECT pg.id, pg.aktivna, pg.zakljucena, pg.godina_start, pg.godina_end " +
            "from poslovna_godina pg where ?1 between pg.godina_start and pg.godina_end "
            ,nativeQuery = true)
    PoslovnaGodina fetchGodinaByDate(Timestamp timestamp);
}
