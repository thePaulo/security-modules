//package br.gov.sead.pagrn.controller;
//
//import br.gov.sead.motorDeCalculo.model.Contracheque;
//import br.gov.sead.motorDeCalculo.service.ContrachequeService;
//import br.gov.sead.motorDeCalculo.service.DroolsService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/motorDeCalculos")
//public class MotorDeCalculoController {
//
//    private DroolsService droolsService;
//    private ContrachequeService contrachequeService;
//
//    public  MotorDeCalculoController(DroolsService droolsService, ContrachequeService contrachequeService) {
//        this.droolsService = droolsService;
//        this.contrachequeService = contrachequeService;
//    }
//
//    @GetMapping()//precisa ser um método post
//    public ResponseEntity<String> rodarDrools() {
//        try{
//            droolsService.rodarApp();
//            return new ResponseEntity<>("Operação executada com sucesso!", HttpStatus.OK);
//        }catch (IOException e){
//            return new ResponseEntity<>("Falha na operação!", HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/contracheque")
//    public ResponseEntity<List<Contracheque>> findall(){
//        List<Contracheque> list = contrachequeService.findAll();
//        return ResponseEntity.ok().body(list);
//    }
//}
//
