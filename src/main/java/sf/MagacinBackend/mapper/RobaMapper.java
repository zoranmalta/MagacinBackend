package sf.MagacinBackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sf.MagacinBackend.model.Roba;
import sf.MagacinBackend.modelDTO.RobaDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RobaMapper {
    @Autowired
    private GrupaRobaMapper grupaRobaMapper;
    @Autowired
    private JedinicaMereMapper jedinicaMereMapper;

    public Roba toRoba(RobaDTO robaDTO){
        Roba roba=new Roba();
        roba.setId(robaDTO.getId());
        roba.setNaziv(robaDTO.getNaziv());
        roba.setPakovanje(robaDTO.getPakovanje());
        roba.setSifra(robaDTO.getSifra());
        roba.setGrupaRoba(grupaRobaMapper.toGrupaRoba(robaDTO.getGrupaRoba()));
        roba.setJedinicaMere(jedinicaMereMapper.toJedinicaMere(robaDTO.getJedinicaMere()));
        return roba;
    }

    public RobaDTO roRobaDTO(Roba roba){
        RobaDTO robaDTO=new RobaDTO();
        robaDTO.setId(roba.getId());
        robaDTO.setSifra(roba.getSifra());
        robaDTO.setPakovanje(roba.getPakovanje());
        robaDTO.setNaziv(roba.getNaziv());
        robaDTO.setGrupaRoba(grupaRobaMapper.toGrupaRobaDTO(roba.getGrupaRoba()));
        robaDTO.setJedinicaMere(jedinicaMereMapper.toJedinicaMereDTO(roba.getJedinicaMere()));
        return robaDTO;
    }
    public List<RobaDTO> toListRobaDTO(List<Roba> list){
        return list.stream().map(roba -> roRobaDTO(roba)).collect(Collectors.toList());
    }
}
