package pl.edu.pwsztar.service;


import pl.edu.pwsztar.domain.dto.ResponseDto;
import pl.edu.pwsztar.domain.dto.auth.AuthenticationDto;
import pl.edu.pwsztar.domain.dto.auth.AuthenticationResult;
import pl.edu.pwsztar.domain.dto.cure.ClientDto;

public interface AccessService {
    ResponseDto<Void> register(ClientDto client);
    ResponseDto<AuthenticationResult> authentication(AuthenticationDto authenticationDto);
    void removeHashSession(Long userId);
    ResponseDto<Void> completeEmailVerification(String link);
}
