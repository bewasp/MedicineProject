package pl.edu.pwsztar.service;


import pl.edu.pwsztar.domain.dto.auth.AuthenticationDto;
import pl.edu.pwsztar.domain.dto.auth.AuthenticationResult;
import pl.edu.pwsztar.domain.dto.cure.ClientDto;

public interface AccessService {
    boolean register(ClientDto client);
    AuthenticationResult authentication(AuthenticationDto authenticationDto);
    void removeHashSession(Long userId);
    Boolean completeEmailVerification(String link);
}
