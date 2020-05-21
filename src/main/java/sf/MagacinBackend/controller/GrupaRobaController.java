package sf.MagacinBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sf.MagacinBackend.mapper.GrupaRobaMapper;
import sf.MagacinBackend.modelDTO.GrupaRobaDTO;
import sf.MagacinBackend.service.GrupaRobaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/gruparoba", produces = MediaType.APPLICATION_JSON_VALUE )
public class GrupaRobaController {
    @Autowired
    private GrupaRobaService grupaRobaService;
    @Autowired
    private GrupaRobaMapper grupaRobaMapper;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<GrupaRobaDTO>> getAll(){
        try {
            List<GrupaRobaDTO> list=grupaRobaMapper.toListGrupaRobaDTO(grupaRobaService.getAll());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
