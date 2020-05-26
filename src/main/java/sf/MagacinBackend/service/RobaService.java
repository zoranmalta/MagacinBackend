package sf.MagacinBackend.service;

import sf.MagacinBackend.model.Magacin;
import sf.MagacinBackend.model.Roba;

import java.util.List;

public interface RobaService {
    List<Roba> getAll();
    Roba insertRoba(Roba roba);
    void insertRobaAndRobnaKartica(Roba roba, List<Magacin> magacinList);
}
