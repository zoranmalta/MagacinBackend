package sf.MagacinBackend.service;

import sf.MagacinBackend.model.PrometniDokument;
import sf.MagacinBackend.model.StavkaPrometnogDokumenta;

import java.util.List;

public interface PrometniDokumentService {
    List<PrometniDokument> getAll();
    PrometniDokument insert(PrometniDokument prometniDokument);
    void prometniDokumentStavkeTransaction(PrometniDokument p, List<StavkaPrometnogDokumenta> list);

}
