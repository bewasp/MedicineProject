package pl.edu.pwsztar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.domain.dto.ResponseDto;
import pl.edu.pwsztar.domain.dto.cure.CureDto;
import pl.edu.pwsztar.service.ClientDoseService;
import pl.edu.pwsztar.service.CureService;

import java.util.List;

@Controller
@RequestMapping(value = "/api/cure")
public class CureApiController {
    private final CureService cureService;
    private final ClientDoseService clientDoseService;

    @Autowired
    public CureApiController(ClientDoseService clientDoseService,
                             CureService cureService){
        this.cureService=cureService;
        this.clientDoseService = clientDoseService;
    }

    @CrossOrigin
    @PostMapping(value = "/{userId}/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseDto<Void>> createCure(@PathVariable Long userId, @RequestBody CureDto cure) {
        ResponseDto<Void> result = null;

        result = cureService.createNewCure(userId, cure);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{userId}/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseDto<Void>> deleteCure(@PathVariable Long userId, @RequestBody CureDto cure) {
        ResponseDto<Void> result = null;

        result = cureService.deleteCure(userId,cure);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/{userId}/allCures", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CureDto>> getCure(@PathVariable Long userId) {
        List<CureDto> data = null;

        data = clientDoseService.getAllCure(userId);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
