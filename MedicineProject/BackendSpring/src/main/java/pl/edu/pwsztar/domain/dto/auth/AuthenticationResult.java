package pl.edu.pwsztar.domain.dto.auth;

import java.io.Serializable;

public class AuthenticationResult implements Serializable {
    private String token;

    public AuthenticationResult(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
