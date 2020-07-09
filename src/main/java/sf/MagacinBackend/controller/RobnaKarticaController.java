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

    @RequestMapping(value="/izvestajlager/{id}",method=RequestMethod.GET)
    public ResponseEntity<Void> izvestajLager(@PathVariable("id") Long id){
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

    @RequestMapping(value="/izvestajanalitika/{id}",method=RequestMethod.GET)
    public ResponseEntity<Void> izvestajAnalitika(@PathVariable("id") Long id){
        try {
            Connection conn;
            conn =
                    (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/magacin?useSSL=false&" +
                            "user=root&password=Lukaivuk");
            HashMap map = new HashMap();
            map.put("robnaKarticaId",id);
            JasperReport jasReport = (JasperReport) JRLoader.loadObjectFromFile("A:/PoslovnaInformatikaBackend/MagacinBackend/src/main/resources/RobnaKartica.jasper");
            JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, map, conn);
            File pdf = new File("A:/PoslovnaInformatikaBackend/MagacinBackend/src/main/resources/robnaKartica.pdf");
            JasperExportManager.exportReportToPdfStream(jasPrint, new FileOutputStream(pdf));
            System.out.println("Temp file : " + pdf.getAbsolutePath());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
