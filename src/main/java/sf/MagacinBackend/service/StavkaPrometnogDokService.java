package sf.MagacinBackend.service;

import sf.MagacinBackend.model.StavkaPrometnogDokumenta;

import java.util.List;

public interface StavkaPrometnogDokService {

    List<StavkaPrometnogDokumenta> insertAll(List<StavkaPrometnogDokumenta> stavke);
    void deleteAll(List<StavkaPrometnogDokumenta> stavke);
}
