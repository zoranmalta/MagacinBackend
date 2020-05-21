package sf.MagacinBackend.mapper;

import org.springframework.stereotype.Component;
import sf.MagacinBackend.model.Mesto;
import sf.MagacinBackend.modelDTO.MestoDTO;

@Component
public class MestoMapper {
    public Mesto toMesto(MestoDTO mestoDTO){
        Mesto mesto= new Mesto();
        mesto.setId(mestoDTO.getId());
        mesto.setNaziv(mestoDTO.getNaziv());
        mesto.setPtt(mestoDTO.getPtt());
        return mesto;
    }
    public MestoDTO toMestoDTO(Mesto mesto){
        MestoDTO mestoDTO=new MestoDTO();
        mestoDTO.setId(mesto.getId());
        mestoDTO.setNaziv(mesto.getNaziv());
        mestoDTO.setPtt(mesto.getPtt());
        return mestoDTO;
    }
}
