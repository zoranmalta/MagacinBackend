package sf.MagacinBackend.mapper;

import org.springframework.stereotype.Component;
import sf.MagacinBackend.model.GrupaRoba;
import sf.MagacinBackend.modelDTO.GrupaRobaDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupaRobaMapper {

    public GrupaRoba toGrupaRoba(GrupaRobaDTO grupaRobaDTO){
        GrupaRoba grupaRoba=new GrupaRoba();
        grupaRoba.setId(grupaRobaDTO.getId());
        grupaRoba.setNaziv(grupaRobaDTO.getNaziv());
        return grupaRoba;
    }

    public GrupaRobaDTO toGrupaRobaDTO(GrupaRoba grupaRoba){
        GrupaRobaDTO grupaRobaDTO=new GrupaRobaDTO();
        grupaRobaDTO.setId(grupaRoba.getId());
        grupaRobaDTO.setNaziv(grupaRoba.getNaziv());
        return grupaRobaDTO;
    }

    public List<GrupaRobaDTO> toListGrupaRobaDTO(List<GrupaRoba> grupaRobaList){
        return grupaRobaList.stream().map(grupaRoba -> toGrupaRobaDTO(grupaRoba))
                .collect(Collectors.toList());
    }
}
