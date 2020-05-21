package sf.MagacinBackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sf.MagacinBackend.model.Preduzece;
import sf.MagacinBackend.modelDTO.PreduzeceDTO;

@Component
public class PreduzeceMapper {
    @Autowired
    private MestoMapper mestoMapper;

    public Preduzece toPreduzece(PreduzeceDTO preduzeceDTO){
        Preduzece preduzece=new Preduzece();
        preduzece.setId(preduzeceDTO.getId());
        preduzece.setAdresa(preduzeceDTO.getAdresa());
        preduzece.setMesto(mestoMapper.toMesto(preduzeceDTO.getMesto()));
        preduzece.setNaziv(preduzeceDTO.getNaziv());
        preduzece.setAdresa(preduzeceDTO.getAdresa());
        return preduzece;
    }
    public PreduzeceDTO toPreduzeceDto(Preduzece preduzece){
        PreduzeceDTO preduzeceDTO=new PreduzeceDTO();
        preduzeceDTO.setId(preduzece.getId());
        preduzeceDTO.setAdresa(preduzece.getAdresa());
        preduzeceDTO.setNaziv(preduzece.getNaziv());
        preduzeceDTO.setPib(preduzece.getPib());
        preduzeceDTO.setMesto(mestoMapper.toMestoDTO(preduzece.getMesto()));
        return preduzeceDTO;
    }
}
