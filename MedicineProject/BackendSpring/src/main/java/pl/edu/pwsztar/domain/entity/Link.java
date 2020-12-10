package pl.edu.pwsztar.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="links" , schema = "medicine")
public class Link implements Serializable {
    @Id
    private Long id_client;

    @Column(name = "link")
    private String link;

    public Link() {
    }

    private Link(Builder builder) {
        this.id_client = builder.id_client;
        this.link = builder.link;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Client client;

    public Long getId_client() {
        return id_client;
    }

    public String getLink() {
        return link;
    }

    public Client getClient() {
        return client;
    }

    public static final class Builder {
        private Long id_client;
        private String link;
        private Client client;

        public Builder() {
        }

        public Builder id_client(Long id_client) {
            this.id_client = id_client;
            return this;
        }

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        public Link build(){
            return new Link(this);
        }
    }
}
