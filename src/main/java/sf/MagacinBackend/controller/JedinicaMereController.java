package sf.MagacinBackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sf.MagacinBackend.mapper.JedinicaMereMapper;
import sf.MagacinBackend.modelDTO.JedinicaMereDTO;
import sf.MagacinBackend.service.JedinicaMereService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/jedinicamere", produces = MediaType.APPLICATION_JSON_VALUE )
public class JedinicaMereController {
    @Autowired
    private JedinicaMereService jedinicaMereService;
    @Autowired
    private JedinicaMereMapper jedinicaMereMapper;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<JedinicaMereDTO>> getAll(){
        try{
            List<JedinicaMereDTO> list=jedinicaMereMapper
                    .toListJedinicaMereDTO(jedinicaMereService.getAll());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
