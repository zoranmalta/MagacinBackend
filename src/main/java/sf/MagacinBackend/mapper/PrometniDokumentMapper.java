package sf.MagacinBackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sf.MagacinBackend.model.PoslovniPartner;
import sf.MagacinBackend.model.PrometniDokument;
import sf.MagacinBackend.modelDTO.PrometniDokumentDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrometniDokumentMapper {
    @Autowired
    private MagacinMapper magacinMapper;
    @Autowired
    private PoslovniPartnerMapper poslovniPartnerMapper;
    @Autowired
    private PoslovnaGodinaMapper poslovnaGodinaMapper;
    @Autowired
    private StavkaPrometnogDokMapper stavkaPrometnogDokMapper;

    public PrometniDokument toPrometniDokument(PrometniDokumentDTO prometniDokumentDTO){
        PrometniDokument p=new PrometniDokument();
        p.setId(prometniDokumentDTO.getId());
        p.setDatumFormiranja(prometniDokumentDTO.getDatumFormiranja());
        p.setDatumKnjizenja(prometniDokumentDTO.getDatumKnjizenja());
        p.setMagacin(magacinMapper.toMagacin(prometniDokumentDTO.getMagacin()));
        if(prometniDokumentDTO.getMagacin2()!=null) {
            p.setMagacin2(magacinMapper.toMagacin(prometniDokumentDTO.getMagacin2()));
        }
        if(prometniDokumentDTO.getPoslovniPartner()!=null){
            p.setPoslovniPartner(poslovniPartnerMapper.toPoslovniPartner(prometniDokumentDTO
                    .getPoslovniPartner()));
        }
        p.setPoslovnaGodina(poslovnaGodinaMapper.toPoslovnaGodina(prometniDokumentDTO.getPoslovnaGodina()));
        p.setRedniBroj(prometniDokumentDTO.getRedniBroj());
        p.setStatusDokumenta(prometniDokumentDTO.getStatusDokumenta());
        p.setTipPrometnogDokumenta(prometniDokumentDTO.getTipPrometnogDokumenta());
        return p;
    }

    public PrometniDokumentDTO toPrometniDokumentDTO(PrometniDokument prometniDokument){
        PrometniDokumentDTO p=new PrometniDokumentDTO();
        p.setId(prometniDokument.getId());
        p.setDatumFormiranja(prometniDokument.getDatumFormiranja());
        p.setDatumKnjizenja(prometniDokument.getDatumKnjizenja());
        p.setRedniBroj(prometniDokument.getRedniBroj());
        p.setStatusDokumenta(prometniDokument.getStatusDokumenta());
        p.setTipPrometnogDokumenta(prometniDokument.getTipPrometnogDokumenta());
        p.setMagacin(magacinMapper.toMagacinDTO(prometniDokument.getMagacin()));
        p.setPoslovnaGodina(poslovnaGodinaMapper.toPoslovnaGodinaDTO(prometniDokument.getPoslovnaGodina()));
        if(prometniDokument.getPoslovniPartner()!=null){
            p.setPoslovniPartner(poslovniPartnerMapper.toPoslovniPartnerDTO(prometniDokument
                    .getPoslovniPartner()));
        }
        if(prometniDokument.getMagacin2()!=null){
            p.setMagacin2(magacinMapper.toMagacinDTO(prometniDokument.getMagacin2()));
        }
        return p;
    }
    public List<PrometniDokumentDTO> toListPrometniDokumentDTO(List<PrometniDokument> list){
        return list.stream().map(prometniDokument -> toPrometniDokumentDTO(prometniDokument))
                .collect(Collectors.toList());
    }
}
