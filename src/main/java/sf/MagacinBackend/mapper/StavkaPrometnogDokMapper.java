package sf.MagacinBackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sf.MagacinBackend.model.PrometniDokument;
import sf.MagacinBackend.model.StavkaPrometnogDokumenta;
import sf.MagacinBackend.modelDTO.StavkaPrometnogDokumentaDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StavkaPrometnogDokMapper {
    @Autowired
    private PrometniDokumentMapper prometniDokumentMapper;
    @Autowired
    private RobaMapper robaMapper;

    public StavkaPrometnogDokumenta toStavkaPrometnogDokumenta(StavkaPrometnogDokumentaDTO stavkaPrometnogDokumentaDTO){
        StavkaPrometnogDokumenta s=new StavkaPrometnogDokumenta();
        s.setId(stavkaPrometnogDokumentaDTO.getId());
        s.setCena(stavkaPrometnogDokumentaDTO.getCena());
        s.setKolicina(stavkaPrometnogDokumentaDTO.getKolicina());
        s.setVrednost(stavkaPrometnogDokumentaDTO.getVrednost());
        if(stavkaPrometnogDokumentaDTO.getPrometniDokument()!=null){
            s.setPrometniDokument(prometniDokumentMapper.toPrometniDokument(
                    stavkaPrometnogDokumentaDTO.getPrometniDokument()));
        }
        s.setRoba(robaMapper.toRoba( stavkaPrometnogDokumentaDTO.getRoba()));
        return s;
    }
    public StavkaPrometnogDokumentaDTO toStavkaPrometnogDokumentaDTO(StavkaPrometnogDokumenta stavkaPrometnogDokumenta){
        StavkaPrometnogDokumentaDTO s=new StavkaPrometnogDokumentaDTO();
        s.setId(stavkaPrometnogDokumenta.getId());
        s.setCena(stavkaPrometnogDokumenta.getCena());
        s.setKolicina(stavkaPrometnogDokumenta.getKolicina());
        s.setVrednost(stavkaPrometnogDokumenta.getVrednost());
        if(stavkaPrometnogDokumenta.getPrometniDokument()!=null){
            s.setPrometniDokument(prometniDokumentMapper.toPrometniDokumentDTO(
                    stavkaPrometnogDokumenta.getPrometniDokument()));
        }
        s.setRoba(robaMapper.roRobaDTO(stavkaPrometnogDokumenta.getRoba()));
        return s;
    }
    public List<StavkaPrometnogDokumentaDTO> toListStavkaDTO(List<StavkaPrometnogDokumenta> list){
        return list.stream().map(stavkaPrometnogDokumenta ->
                toStavkaPrometnogDokumentaDTO(stavkaPrometnogDokumenta)).collect(Collectors.toList());
    }

}
