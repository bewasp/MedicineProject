package pl.edu.pwsztar.service;


import pl.edu.pwsztar.domain.dto.AuthenticationDto;
import pl.edu.pwsztar.domain.dto.AuthenticationResult;
import pl.edu.pwsztar.domain.dto.ClientDto;

public interface AccessService {
    boolean register(ClientDto client);
    AuthenticationResult authentication(AuthenticationDto authenticationDto);
    void removeHashSession(Long userId);
}
