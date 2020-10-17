package pl.edu.pwsztar.domain.dto;

import java.io.Serializable;

public class AuthenticationDto implements Serializable {
    private String email;
    private String password;

    public AuthenticationDto() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
