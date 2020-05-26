package sf.MagacinBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sf.MagacinBackend.mapper.PoslovnaGodinaMapper;
import sf.MagacinBackend.mapper.PrometniDokumentMapper;
import sf.MagacinBackend.mapper.StavkaPrometnogDokMapper;
import sf.MagacinBackend.model.*;
import sf.MagacinBackend.modelDTO.PrometniDokumentDTO;
import sf.MagacinBackend.modelDTO.StavkaPrometnogDokumentaDTO;
import sf.MagacinBackend.service.PoslovnaGodinaService;
import sf.MagacinBackend.service.RobnaKarticaService;
import sf.MagacinBackend.service.StavkaPrometnogDokService;
import sf.MagacinBackend.service.serviceImpl.PrometniDokumentServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/prometnidokument", produces = MediaType.APPLICATION_JSON_VALUE )
public class PrometniDokumentController {
    @Autowired
    private PrometniDokumentServiceImpl prometniDokumentService;
    @Autowired
    private StavkaPrometnogDokService stavkaPrometnogDokService;
    @Autowired
    private PoslovnaGodinaService poslovnaGodinaService;
    @Autowired
    private RobnaKarticaService robnaKarticaService;
    @Autowired
    PrometniDokumentMapper prometniDokumentMapper;
    @Autowired
    private PoslovnaGodinaMapper poslovnaGodinaMapper;
    @Autowired
    StavkaPrometnogDokMapper stavkaPrometnogDokMapper;

    @RequestMapping(value = "/insertprijemnica",method = RequestMethod.POST)
    public ResponseEntity<PrometniDokumentDTO> insert(@RequestBody PrometniDokumentDTO prometniDokumentDTO){
        System.out.println("Stiglo sa klijenta : "+prometniDokumentDTO.toString());
        try {
            PoslovnaGodina godina=poslovnaGodinaService.findOneByDate(prometniDokumentDTO.getDatumFormiranja());
            prometniDokumentDTO.setPoslovnaGodina(poslovnaGodinaMapper.toPoslovnaGodinaDTO(godina));
            PrometniDokument p= prometniDokumentMapper.toPrometniDokument(prometniDokumentDTO);
            List<StavkaPrometnogDokumenta> list=prometniDokumentDTO.getStavke().stream().map(
                    stavkaPrometnogDokumentaDTO -> stavkaPrometnogDokMapper.toStavkaPrometnogDokumenta
                            (stavkaPrometnogDokumentaDTO)).collect(Collectors.toList());

            prometniDokumentService.prometniDokumentStavkeTransaction(p,list);
            PrometniDokumentDTO prometniDokumentDTO1=new PrometniDokumentDTO();
            prometniDokumentDTO1=prometniDokumentMapper.toPrometniDokumentDTO(p);
            List<StavkaPrometnogDokumentaDTO> dtos=stavkaPrometnogDokMapper.toListStavkaDTO(list);
            prometniDokumentDTO1.setStavke(dtos);
            return new ResponseEntity<>(prometniDokumentDTO1, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
