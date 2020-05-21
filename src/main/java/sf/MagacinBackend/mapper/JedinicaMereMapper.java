package sf.MagacinBackend.mapper;

import org.springframework.stereotype.Component;
import sf.MagacinBackend.model.JedinicaMere;
import sf.MagacinBackend.modelDTO.JedinicaMereDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JedinicaMereMapper {
    public JedinicaMere toJedinicaMere(JedinicaMereDTO jedinicaMereDTO){
        JedinicaMere jedinicaMere=new JedinicaMere();
        jedinicaMere.setId(jedinicaMereDTO.getId());
        jedinicaMere.setNaziv(jedinicaMereDTO.getNaziv());
        return jedinicaMere;
    }
    public JedinicaMereDTO toJedinicaMereDTO(JedinicaMere jedinicaMere){
        JedinicaMereDTO jedinicaMereDTO=new JedinicaMereDTO();
        jedinicaMereDTO.setId(jedinicaMere.getId());
        jedinicaMereDTO.setNaziv(jedinicaMere.getNaziv());
        return jedinicaMereDTO;
    }
    public List<JedinicaMereDTO> toListJedinicaMereDTO(List<JedinicaMere> list){
        return list.stream().map(jedinicaMere -> toJedinicaMereDTO(jedinicaMere))
                .collect(Collectors.toList());
    }
}
