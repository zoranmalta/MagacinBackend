package sf.MagacinBackend.service;

import sf.MagacinBackend.model.PoslovnaGodina;

import java.sql.Timestamp;

public interface PoslovnaGodinaService {
    PoslovnaGodina findOneByDate(Timestamp timestamp);
}
