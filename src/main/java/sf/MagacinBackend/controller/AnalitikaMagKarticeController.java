package sf.MagacinBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sf.MagacinBackend.mapper.AnalitikaMagKarticeMapper;
import sf.MagacinBackend.model.AnalitikaMagacinskeKartice;
import sf.MagacinBackend.model.RobnaKartica;
import sf.MagacinBackend.modelDTO.AnalitikaMagacinskeKarticeDTO;
import sf.MagacinBackend.modelDTO.PrometniDokumentDTO;
import sf.MagacinBackend.service.AnalitikaMagKarticeService;
import sf.MagacinBackend.service.RobnaKarticaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/analitika", produces = MediaType.APPLICATION_JSON_VALUE )
public class AnalitikaMagKarticeController {

    @Autowired
    private AnalitikaMagKarticeService analitikaMagKarticeService;
    @Autowired
    private AnalitikaMagKarticeMapper analitikaMagKarticeMapper;
    @Autowired
    private RobnaKarticaService robnaKarticaService;

    @RequestMapping(value="/kartica/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<AnalitikaMagacinskeKarticeDTO>> getAll(@PathVariable("id") Long id){
        try {
            RobnaKartica r=robnaKarticaService.getOneById(id);
            List<AnalitikaMagacinskeKartice> list=analitikaMagKarticeService.getAllByRobnaKartica(r);
            //sortiram listu po datumu
            list.sort((o1, o2) -> o1.getDatumFormiranja().compareTo(o2.getDatumFormiranja()));
            return new ResponseEntity<>(analitikaMagKarticeMapper.toListAnalitikaDTO(list), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}
