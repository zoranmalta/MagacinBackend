package sf.MagacinBackend.controller;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sf.MagacinBackend.mapper.RobaMapper;
import sf.MagacinBackend.model.Magacin;
import sf.MagacinBackend.model.Roba;
import sf.MagacinBackend.modelDTO.RobaDTO;
import sf.MagacinBackend.service.MagacinService;
import sf.MagacinBackend.service.RobaService;
import sf.MagacinBackend.service.serviceImpl.RobnaKarticaServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/roba", produces = MediaType.APPLICATION_JSON_VALUE )
public class RobaController {
    @Autowired
    private RobaService robaService;
    @Autowired
    private MagacinService magacinService;
    @Autowired
    private RobnaKarticaServiceImpl robnaKarticaService;
    @Autowired
    private RobaMapper robaMapper;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<RobaDTO>> getAll(){
        try {
            List<RobaDTO> list=robaMapper.toListRobaDTO(robaService.getAll());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<RobaDTO> insertRoba(@RequestBody RobaDTO robaDTO){
        System.out.println("pristiglo sa servera "+robaDTO.toString());
        try {
            Roba roba=robaMapper.toRoba(robaDTO);
            List<Magacin> magacinList=magacinService.getAll();

            robaService.insertRobaAndRobnaKartica(roba,magacinList);

            return new ResponseEntity<>(robaMapper.roRobaDTO(roba),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/izvestaj",method=RequestMethod.GET)
    public ResponseEntity<Void> izvestaj(){
        try {
            Connection conn;
            conn =
                    (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/magacin?useSSL=false&" +
                            "user=root&password=Lukaivuk");
            HashMap map = new HashMap();
            JasperReport jasReport = (JasperReport) JRLoader.loadObjectFromFile("A:/PoslovnaInformatikaBackend/MagacinBackend/src/main/resources/Artikli.jasper");
            JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, map, conn);
            File pdf = new File("A:/PoslovnaInformatikaBackend/MagacinBackend/src/main/resources/artikli.pdf");
            JasperExportManager.exportReportToPdfStream(jasPrint, new FileOutputStream(pdf));
            System.out.println("Temp file : " + pdf.getAbsolutePath());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value="/izvestaj2",method=RequestMethod.GET)
    public ResponseEntity getReport() {
        try {
            Connection conn;
            conn =
                    (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/magacin?useSSL=false&" +
                            "user=root&password=Lukaivuk");
            HashMap map = new HashMap();
            JasperReport jasReport = (JasperReport) JRLoader.loadObjectFromFile("A:/PoslovnaInformatikaBackend/MagacinBackend/src/main/resources/Artikli.jasper");
            JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, map, conn);
            ByteArrayInputStream bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasPrint));//ExportReportToPdf vraca byte[]

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (Exception e) {
            e.printStackTrace();
           return null;
        }

    }

}
