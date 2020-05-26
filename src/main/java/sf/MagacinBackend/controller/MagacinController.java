package sf.MagacinBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sf.MagacinBackend.mapper.MagacinMapper;
import sf.MagacinBackend.modelDTO.MagacinDTO;
import sf.MagacinBackend.service.MagacinService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/magacin", produces = MediaType.APPLICATION_JSON_VALUE )
public class MagacinController {
    @Autowired
    private MagacinService magacinService;
    @Autowired
    private MagacinMapper magacinMapper;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<MagacinDTO>> getAll(){
        try {
            List<MagacinDTO> list = magacinMapper.toListMagacinDTO(magacinService.getAll());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
