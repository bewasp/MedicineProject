package pl.edu.pwsztar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.domain.dto.auth.AuthenticationDto;
import pl.edu.pwsztar.domain.dto.auth.AuthenticationResult;
import pl.edu.pwsztar.domain.dto.cure.ClientDto;
import pl.edu.pwsztar.service.AccessService;

@Controller
@RequestMapping(value = "/api/user")
public class AccessApiController {

    private AccessService accessService;

    @Autowired
    public AccessApiController(AccessService accessService){
        this.accessService=accessService;
    }


    @CrossOrigin
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> register(@RequestBody ClientDto client) {
        boolean result = false;

        if(client != null){
            result = accessService.register(client);
        }

        return new ResponseEntity<>(result , HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AuthenticationResult> login(@RequestBody AuthenticationDto client) {
        AuthenticationResult result = null;

        if(client != null){
            result = accessService.authentication(client);
        }

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/logout/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> logout(@PathVariable Long userId) {

        accessService.removeHashSession(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
