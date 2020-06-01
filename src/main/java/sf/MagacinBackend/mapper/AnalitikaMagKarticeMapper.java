package sf.MagacinBackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sf.MagacinBackend.model.AnalitikaMagacinskeKartice;
import sf.MagacinBackend.modelDTO.AnalitikaMagacinskeKarticeDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnalitikaMagKarticeMapper {
    @Autowired
    private RobnaKarticaMapper robnaKarticaMapper;
    @Autowired
    private StavkaPrometnogDokMapper stavkaPrometnogDokMapper;

    AnalitikaMagacinskeKartice toAnalitikaMagacinskeKartice(AnalitikaMagacinskeKarticeDTO aDto){
        AnalitikaMagacinskeKartice a=new AnalitikaMagacinskeKartice();
        a.setId(aDto.getId());
        a.setCena(aDto.getCena());
        a.setDatumFormiranja(aDto.getDatumFormiranja());
        a.setKolicina(aDto.getKolicina());
        a.setRedniBroj(aDto.getRedniBroj());
        a.setVrednost(aDto.getVrednost());
        a.setSmer(aDto.getSmer());
        a.setTipPrometa(aDto.getTipPrometa());
        a.setRobnaKartica(robnaKarticaMapper.toRobnaKartica(aDto.getRobnaKartica()));
        a.setStavkaDokumenta(stavkaPrometnogDokMapper.toStavkaPrometnogDokumenta(aDto.getStavkaDokumenta()));
        return a;
    }

    public AnalitikaMagacinskeKarticeDTO toAnalitikaMagKarticeDTO(AnalitikaMagacinskeKartice amk){
        AnalitikaMagacinskeKarticeDTO a=new AnalitikaMagacinskeKarticeDTO();
        a.setId(amk.getId());
        a.setCena(amk.getCena());
        a.setRedniBroj(amk.getRedniBroj());
        a.setKolicina(amk.getKolicina());
        a.setVrednost(amk.getVrednost());
        a.setSmer(amk.getSmer());
        a.setTipPrometa(amk.getTipPrometa());
        a.setDatumFormiranja(amk.getDatumFormiranja());
        a.setRobnaKartica(robnaKarticaMapper.toRobnaKarticaDTO(amk.getRobnaKartica()));
        a.setStavkaDokumenta(stavkaPrometnogDokMapper.toStavkaPrometnogDokumentaDTO(amk.getStavkaDokumenta()));
        return a;

    }
    public List<AnalitikaMagacinskeKarticeDTO> toListAnalitikaDTO(List<AnalitikaMagacinskeKartice> list){
        return list.stream().map(analitikaMagKartice -> toAnalitikaMagKarticeDTO(analitikaMagKartice))
                .collect(Collectors.toList());
    }
}
