package sf.MagacinBackend.service;

import sf.MagacinBackend.model.PrometniDokument;
import sf.MagacinBackend.model.StavkaPrometnogDokumenta;

import java.util.List;

public interface PrometniDokumentService {
    List<PrometniDokument> getAll();
    PrometniDokument insert(PrometniDokument prometniDokument);
    void insertPrijemnicaTransaction(PrometniDokument p, List<StavkaPrometnogDokumenta> list);
    void knjizenjePrijemnice(PrometniDokument prometniDokument);
    boolean proveriRobneKarticeIKolicinuSvake(PrometniDokument p, List<StavkaPrometnogDokumenta> list);
    void insertOtpremnicaTransaction(PrometniDokument p, List<StavkaPrometnogDokumenta> list);
    void knjizenjeOtpremnice(PrometniDokument prometniDokument);
    void insertMMTransaction(PrometniDokument p, List<StavkaPrometnogDokumenta> list);

}
