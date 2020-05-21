package sf.MagacinBackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sf.MagacinBackend.model.Magacin;
import sf.MagacinBackend.modelDTO.MagacinDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MagacinMapper {
    @Autowired
    private PreduzeceMapper preduzeceMapper;

    public Magacin toMagacin(MagacinDTO magacinDTO){
        Magacin magacin=new Magacin();
        magacin.setId(magacinDTO.getId());
        magacin.setNaziv(magacinDTO.getNaziv());
        magacin.setPreduzece(preduzeceMapper.toPreduzece(magacinDTO.getPreduzece()));
        return magacin;
    }
    public MagacinDTO toMagacinDTO(Magacin magacin){
        MagacinDTO magacinDTO=new MagacinDTO();
        magacinDTO.setId(magacin.getId());
        magacinDTO.setNaziv(magacin.getNaziv());
        magacinDTO.setPreduzece(preduzeceMapper.toPreduzeceDto(magacin.getPreduzece()));
        return magacinDTO;
    }
    public List<MagacinDTO> toListMagacinDTO(List<Magacin> magacini){
        return magacini.stream().map(magacin -> toMagacinDTO(magacin)).collect(Collectors.toList());
    }
}
