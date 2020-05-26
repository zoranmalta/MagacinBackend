package sf.MagacinBackend.service;

import sf.MagacinBackend.model.PrometniDokument;

import java.util.List;

public interface PrometniDokumentService {
    List<PrometniDokument> getAll();
    PrometniDokument insert(PrometniDokument prometniDokument);

}
