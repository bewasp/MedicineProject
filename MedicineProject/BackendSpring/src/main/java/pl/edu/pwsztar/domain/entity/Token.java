package pl.edu.pwsztar.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tokens")
public class Token implements Serializable{
    @Id
    @Column(name="token_id")
    private Long userId;

    @Column(name="token")
    private String token;

    public Token() {
    }

    public Token(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
