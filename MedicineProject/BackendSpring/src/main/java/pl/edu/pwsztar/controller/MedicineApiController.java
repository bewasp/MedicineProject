package pl.pwsztar.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.domain.entity.Client;
import pl.edu.pwsztar.service.MedicineService;

import java.util.Optional;

@Controller
@RequestMapping(value = "/medicine")
public class MedicineApiController {

    private MedicineService medicineService;

    @Autowired
    public MedicineApiController(MedicineService medicineService){
        this.medicineService=medicineService;
    }


    @CrossOrigin
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> register(@RequestBody Client clientPersonal) {
        Boolean result = false;
        Optional<Client> checkObject = Optional.of(clientPersonal);

        if(!checkObject.isEmpty()){
            medicineService.register(clientPersonal);
            result = true;
        }

        return new ResponseEntity<>(result , HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> login(@RequestBody Client clientPersonal) {
        Boolean result = false;
        Optional<Client> checkObject = Optional.of(clientPersonal);

        if(!checkObject.isEmpty()){
            result = medicineService.login(clientPersonal);
        }

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
