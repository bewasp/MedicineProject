package pl.edu.pwsztar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/medicine")
public class MedicineApiController {
    @Autowired
    public MedicineApiController(){

    }

    @CrossOrigin
    @GetMapping(value = "/login")
    public ResponseEntity<Void> login(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
