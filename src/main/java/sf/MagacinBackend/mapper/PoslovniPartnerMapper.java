package sf.MagacinBackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import sf.MagacinBackend.model.PoslovniPartner;
import sf.MagacinBackend.modelDTO.PoslovniPartnerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PoslovniPartnerMapper {
    @Autowired
    private MestoMapper mestoMapper;
    @Autowired
    private PreduzeceMapper preduzeceMapper;

    public PoslovniPartner toPoslovniPartner(PoslovniPartnerDTO poslovniPartnerDTO){
        PoslovniPartner poslovniPartner=new PoslovniPartner();
        poslovniPartner.setId(poslovniPartnerDTO.getId());
        poslovniPartner.setAdresa(poslovniPartnerDTO.getAdresa());
        poslovniPartner.setNaziv(poslovniPartnerDTO.getNaziv());
        poslovniPartner.setPib(poslovniPartnerDTO.getPib());
        poslovniPartner.setTipPartnera(poslovniPartnerDTO.getTipPartnera());
        poslovniPartner.setMesto(mestoMapper.toMesto(poslovniPartnerDTO.getMesto()));
        poslovniPartner.setPreduzece(preduzeceMapper.toPreduzece(poslovniPartnerDTO.getPreduzece()));
        return poslovniPartner;
    }
    public PoslovniPartnerDTO toPoslovniPartnerDTO(PoslovniPartner poslovniPartner){
        PoslovniPartnerDTO poslovniPartnerDTO=new PoslovniPartnerDTO();
        poslovniPartnerDTO.setId(poslovniPartner.getId());
        poslovniPartnerDTO.setAdresa(poslovniPartner.getAdresa());
        poslovniPartnerDTO.setNaziv(poslovniPartner.getNaziv());
        poslovniPartnerDTO.setPib(poslovniPartner.getPib());
        poslovniPartnerDTO.setTipPartnera(poslovniPartner.getTipPartnera());
        poslovniPartnerDTO.setMesto(mestoMapper.toMestoDTO(poslovniPartner.getMesto()));
        poslovniPartnerDTO.setPreduzece(preduzeceMapper.toPreduzeceDto(poslovniPartner.getPreduzece()));
        return poslovniPartnerDTO;
    }
    public List<PoslovniPartnerDTO> toListPoslovniDTO(List<PoslovniPartner> list){
        return list.stream().map(poslovniPartner -> toPoslovniPartnerDTO(poslovniPartner))
                .collect(Collectors.toList());
    }

}
