package sf.MagacinBackend.controller;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sf.MagacinBackend.mapper.MagacinMapper;
import sf.MagacinBackend.mapper.RobaMapper;
import sf.MagacinBackend.mapper.RobnaKarticaMapper;
import sf.MagacinBackend.mapper.StavkaPrometnogDokMapper;
import sf.MagacinBackend.model.*;
import sf.MagacinBackend.modelDTO.MagacinDTO;
import sf.MagacinBackend.modelDTO.PrometniDokumentDTO;
import sf.MagacinBackend.modelDTO.RobnaKarticaDTO;
import sf.MagacinBackend.service.MagacinService;
import sf.MagacinBackend.service.PoslovnaGodinaService;
import sf.MagacinBackend.service.RobnaKarticaService;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/robnakartica", produces = MediaType.APPLICATION_JSON_VALUE )
public class RobnaKarticaController {
    @Autowired
    private RobnaKarticaService robnaKarticaService;
    @Autowired
    private RobnaKarticaMapper robnaKarticaMapper;
    @Autowired
    private RobaMapper robaMapper;
    @Autowired
    private MagacinMapper magacinMapper;
    @Autowired
    private MagacinService magacinService;
    @Autowired
    private StavkaPrometnogDokMapper stavkaPrometnogDokMapper;
    @Autowired
    private PoslovnaGodinaService poslovnaGodinaService;

    @RequestMapping(value = "/magacin/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<RobnaKarticaDTO>> lagerListaMagacina(@PathVariable("id") Long id){
        try {
            Magacin m=magacinService.getOne(id);
            PoslovnaGodina godina=poslovnaGodinaService.findOneByDate(new Timestamp(System.currentTimeMillis()));
            List<RobnaKartica> list=robnaKarticaService.getAllByMagacinAndPoslovnaGodina(m,godina);
            list.stream().map(robnaKartica -> {
                if(robnaKartica.izracunajStanjeKartice()){}
                return robnaKartica;
            }).collect(Collectors.toList());
            return new ResponseEntity<>(robnaKarticaMapper.toListRobnaKarticaDTO(list),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/izvestaj/{id}",method=RequestMethod.GET)
    public ResponseEntity<Void> izvestaj(@PathVariable("id") Long id){
        try {
            Connection conn;
            conn =
                    (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/magacin?useSSL=false&" +
                            "user=root&password=Lukaivuk");
            HashMap map = new HashMap();
            map.put("magacinId",id);
            JasperReport jasReport = (JasperReport) JRLoader.loadObjectFromFile("A:/PoslovnaInformatikaBackend/MagacinBackend/src/main/resources/Lager.jasper");
            JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, map, conn);
            File pdf = new File("A:/PoslovnaInformatikaBackend/MagacinBackend/src/main/resources/lager.pdf");
            JasperExportManager.exportReportToPdfStream(jasPrint, new FileOutputStream(pdf));
            System.out.println("Temp file : " + pdf.getAbsolutePath());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //vraca listu kartica i analitika za odredjnu robu dokumenta koji hocemo da insertujemo
    //ako ne postoji kartica duzina liste je manja od liste stavki tog dokumenta za insert
    //todo objasnjeno dole
    @RequestMapping(value = "/karticeoddokumenta",method = RequestMethod.POST)
    public ResponseEntity<List<RobnaKarticaDTO>> getListByPrometniDokument(@RequestBody PrometniDokumentDTO prometniDokumentDTO){
        try {
            List<RobnaKartica> robnaKarticaList=new ArrayList<>();
            List<StavkaPrometnogDokumenta> list=prometniDokumentDTO.getStavke().stream().map
                    (stavkaPrometnogDokumentaDTO -> stavkaPrometnogDokMapper.toStavkaPrometnogDokumenta
                            (stavkaPrometnogDokumentaDTO)).collect(Collectors.toList());
            Magacin m=magacinMapper.toMagacin(prometniDokumentDTO.getMagacin());
            PoslovnaGodina godina=poslovnaGodinaService.findOneByDate(prometniDokumentDTO.getDatumFormiranja());
            for (StavkaPrometnogDokumenta s:list){
                RobnaKartica robnaKartica=robnaKarticaService
                        .getOneByRobaAndMagacinAndPoslovnaGodina(s.getRoba(),m,godina);
                if(robnaKartica==null){
                    continue;
                }
                //todo izvuci listu analitika za svaku karticu i setovani ih u karticu
                robnaKarticaList.add(robnaKartica);
            }
            return new ResponseEntity<>(robnaKarticaMapper.toListRobnaKarticaDTO(robnaKarticaList)
            ,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
