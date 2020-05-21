package sf.MagacinBackend.service;

import sf.MagacinBackend.model.Roba;

import java.util.List;

public interface RobaService {
    List<Roba> getAll();
    Roba insertRoba(Roba roba);
}
