package pl.edu.pwsztar.domain.entity;

import pl.edu.pwsztar.domain.entity.key.ClientDoseKey;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="dose_of_client"  , schema = "medicine")
public class ClientDose implements Serializable {

    @EmbeddedId
    private ClientDoseKey clientDoseKey;

    @ManyToOne
    @MapsId("id_client")
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Client client;

    @ManyToOne
    @MapsId("id_cure")
    @JoinColumn(name = "id_cure", referencedColumnName = "id_cure")
    private Cure cure;


    public ClientDose() {
    }

    private ClientDose(Builder builder) {
        this.clientDoseKey = builder.clientDoseKey;
        this.client = builder.client;
        this.cure = builder.cure;
    }

    public ClientDoseKey getClientDoseKey() {
        return clientDoseKey;
    }

    public Client getClient() {
        return client;
    }

    public Cure getCure() {
        return cure;
    }

    public static final class Builder{
        private ClientDoseKey clientDoseKey;
        private Client client;
        private Cure cure;

        public Builder() {
        }

        public Builder(ClientDose copy) {
            this.clientDoseKey = copy.getClientDoseKey();
            this.client = copy.getClient();
            this.cure = copy.getCure();
        }

        public Builder clientDoseKey(ClientDoseKey clientDoseKey){
            this.clientDoseKey = clientDoseKey;
            return this;
        }

        public Builder client(Client client){
            this.client = client;
            return this;
        }

        public Builder cure(Cure cure){
            this.cure = cure;
            return this;
        }

        public ClientDose build(){
            return new ClientDose(this);
        }
    }

}
