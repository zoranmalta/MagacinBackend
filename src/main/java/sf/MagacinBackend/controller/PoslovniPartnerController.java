package sf.MagacinBackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sf.MagacinBackend.mapper.PoslovniPartnerMapper;
import sf.MagacinBackend.modelDTO.MagacinDTO;
import sf.MagacinBackend.modelDTO.PoslovniPartnerDTO;
import sf.MagacinBackend.service.PoslovniPartnerService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/poslovnipartner", produces = MediaType.APPLICATION_JSON_VALUE )
public class PoslovniPartnerController {
    @Autowired
    private PoslovniPartnerService poslovniPartnerService;
    @Autowired
    private PoslovniPartnerMapper poslovniPartnerMapper;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<PoslovniPartnerDTO>> getAll(){
        try {
            List<PoslovniPartnerDTO> list = poslovniPartnerMapper
                    .toListPoslovniDTO(poslovniPartnerService.getAll());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
