package sf.MagacinBackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import sf.MagacinBackend.model.RobnaKartica;
import sf.MagacinBackend.modelDTO.RobnaKarticaDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RobnaKarticaMapper {
    @Autowired
    private RobaMapper robaMapper;
    @Autowired
    private MagacinMapper magacinMapper;
    @Autowired
    private PoslovnaGodinaMapper poslovnaGodinaMapper;


    public RobnaKartica toRobnaKartica(RobnaKarticaDTO robnaKarticaDTO){
        RobnaKartica r=new RobnaKartica();
        r.setId(robnaKarticaDTO.getId());
        r.setCena(robnaKarticaDTO.getCena());

        r.setKolicinaPocetnogStanja(robnaKarticaDTO.getKolicinaPocetnogStanja());
        r.setKolicinaUlaza(robnaKarticaDTO.getKolicinaUlaza());
        r.setKolicinaIzlaza(robnaKarticaDTO.getKolicinaIzlaza());

        r.setUkupnaKolicina(robnaKarticaDTO.getUkupnaKolicina());
        r.setUkupnaVrednost(robnaKarticaDTO.getUkupnaVrednost());

        r.setVrednostPocetnogStanja(robnaKarticaDTO.getVrednostPocetnogStanja());
        r.setVrednostUlaza(robnaKarticaDTO.getVrednostUlaza());
        r.setVrednostIzlaza(robnaKarticaDTO.getVrednostIzlaza());

        r.setMagacin(magacinMapper.toMagacin(robnaKarticaDTO.getMagacin()));
        r.setRoba(robaMapper.toRoba(robnaKarticaDTO.getRoba()));
        r.setPoslovnaGodina(poslovnaGodinaMapper.toPoslovnaGodina(robnaKarticaDTO.getPoslovnaGodina()));
        return r;
    }

    public RobnaKarticaDTO toRobnaKarticaDTO(RobnaKartica robnaKartica){
        RobnaKarticaDTO r=new RobnaKarticaDTO();
        r.setId(robnaKartica.getId());
        r.setCena(robnaKartica.getCena());

        r.setKolicinaPocetnogStanja(robnaKartica.getKolicinaPocetnogStanja());
        r.setKolicinaUlaza(robnaKartica.getKolicinaUlaza());
        r.setKolicinaIzlaza(robnaKartica.getKolicinaIzlaza());

        r.setUkupnaKolicina(robnaKartica.getUkupnaKolicina());
        r.setUkupnaVrednost(robnaKartica.getUkupnaVrednost());

        r.setVrednostPocetnogStanja(robnaKartica.getVrednostPocetnogStanja());
        r.setVrednostUlaza(robnaKartica.getVrednostUlaza());
        r.setVrednostIzlaza(robnaKartica.getVrednostIzlaza());

        r.setMagacin(magacinMapper.toMagacinDTO(robnaKartica.getMagacin()));
        r.setRoba(robaMapper.roRobaDTO(robnaKartica.getRoba()));
        r.setPoslovnaGodina(poslovnaGodinaMapper.toPoslovnaGodinaDTO(robnaKartica.getPoslovnaGodina()));
        return r;
    }

    public List<RobnaKarticaDTO> toListRobnaKarticaDTO(List<RobnaKartica> list){
        return list.stream().map(robnaKartica -> toRobnaKarticaDTO(robnaKartica))
                .collect(Collectors.toList());
    }
}
