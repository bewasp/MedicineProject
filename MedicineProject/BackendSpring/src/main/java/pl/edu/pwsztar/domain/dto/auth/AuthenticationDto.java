package pl.edu.pwsztar.domain.dto.auth;

import java.io.Serializable;

public class AuthenticationDto implements Serializable {
    private String email;
    private String password;

    public AuthenticationDto() {
    }

    public AuthenticationDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
