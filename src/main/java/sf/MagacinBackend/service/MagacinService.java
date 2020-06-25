package sf.MagacinBackend.service;

import sf.MagacinBackend.model.Magacin;

import java.util.List;

public interface MagacinService {

    List<Magacin> getAll();
    Magacin getOne(Long id);
}
