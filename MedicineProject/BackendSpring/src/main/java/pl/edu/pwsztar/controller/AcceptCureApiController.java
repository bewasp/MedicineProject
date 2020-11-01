package pl.edu.pwsztar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.domain.dto.cure.ClientInfo;
import pl.edu.pwsztar.domain.dto.cure.CureDto;
import pl.edu.pwsztar.service.AcceptingDoseService;

@Controller
@RequestMapping(value = "/api/accept")
public class AcceptCureApiController {

    private final AcceptingDoseService acceptingDoseService;

    @Autowired
    public AcceptCureApiController(AcceptingDoseService acceptingDoseService){
        this.acceptingDoseService=acceptingDoseService;
    }


    @CrossOrigin
    @PostMapping(value = "/{userId}/apply-accept", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> accepting(@PathVariable Long userId, @RequestBody CureDto cure) {
        boolean result = false;

        result = acceptingDoseService.acceptingCure(userId, cure);

        return new ResponseEntity<>(result , HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "/{userId}/get-stats", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClientInfo> stats(@PathVariable Long userId) {
        ClientInfo result = null;

        result = acceptingDoseService.makeStats(userId);

        return new ResponseEntity<>(result , HttpStatus.CREATED);
    }
}
