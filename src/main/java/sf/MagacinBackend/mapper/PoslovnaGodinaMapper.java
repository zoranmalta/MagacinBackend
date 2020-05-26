package sf.MagacinBackend.mapper;

import org.springframework.stereotype.Component;
import sf.MagacinBackend.model.PoslovnaGodina;
import sf.MagacinBackend.modelDTO.PoslovnaGodinaDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PoslovnaGodinaMapper {
    public PoslovnaGodina toPoslovnaGodina(PoslovnaGodinaDTO poslovnaGodinaDTO){
        PoslovnaGodina p=new PoslovnaGodina();
        p.setId(poslovnaGodinaDTO.getId());
        p.setAktivna(poslovnaGodinaDTO.isAktivna());
        p.setZakljucena(poslovnaGodinaDTO.isZakljucena());
        p.setGodinaStart(poslovnaGodinaDTO.getGodinaStart());
        p.setGodinaEnd(poslovnaGodinaDTO.getGodinaEnd());
        return p;
    }
    public PoslovnaGodinaDTO toPoslovnaGodinaDTO(PoslovnaGodina poslovnaGodina){
        PoslovnaGodinaDTO p=new PoslovnaGodinaDTO();
        p.setId(poslovnaGodina.getId());
        p.setAktivna(poslovnaGodina.isAktivna());
        p.setZakljucena(poslovnaGodina.isZakljucena());
        p.setGodinaStart(poslovnaGodina.getGodinaStart());
        p.setGodinaEnd(poslovnaGodina.getGodinaEnd());
        return p;
    }
    public List<PoslovnaGodinaDTO> toListPoslovnaGodinaDTO(List<PoslovnaGodina> list){
        return list.stream().map(poslovnaGodina -> toPoslovnaGodinaDTO(poslovnaGodina))
                .collect(Collectors.toList());
    }
}
