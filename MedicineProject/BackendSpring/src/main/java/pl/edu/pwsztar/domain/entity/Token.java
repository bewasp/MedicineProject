package pl.edu.pwsztar.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tokens" , schema = "medicine")
public class Token implements Serializable {
    @Id
    private Long id_client;

    @Column(name="token")
    private String token;

    public Token() {
    }

    private Token(Builder builder) {
        this.id_client = builder.userId;
        this.token = builder.token;
        this.client = builder.client;
    }

    @ManyToOne
    @MapsId("id_client")
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Client client;

    public Long getId_client() {
        return id_client;
    }

    public String getToken() {
        return token;
    }

    public Client getClient() {
        return client;
    }

    public static final class Builder{
        private Long userId;
        private String token;
        private Client client;

        public Builder userId(Long userId){
            this.userId = userId;
            return this;
        }

        public Builder token(String token){
            this.token = token;
            return this;
        }

        public Builder client(Client client){
            this.client = client;
            return this;
        }

        public Token build(){
            return new Token(this);
        }
    }
}
