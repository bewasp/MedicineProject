package pl.edu.pwsztar.domain.entity;


import pl.edu.pwsztar.domain.entity.key.AcceptedDoseKey;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="accepted_dose"  , schema = "medicine")
public class AcceptedDose implements Serializable {

    @EmbeddedId
    private AcceptedDoseKey id;

    @ManyToOne
    @MapsId("id_client")
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @MapsId("id_cure")
    @JoinColumn(name = "id_cure")
    private Cure cure;

    @Column(name="accepted")
    private boolean accepted;

    @Column(name="delayed")
    private boolean delayed;

    @Column(name="date")
    private String date;

    public AcceptedDose() {
    }

    private AcceptedDose(Builder builder) {
        this.id = builder.id;
        this.client = builder.client;
        this.cure = builder.cure;
        this.date = builder.date;
        this.accepted = builder.accepted;
        this.delayed = builder.delayed;
    }

    public AcceptedDoseKey getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Cure getCure() {
        return cure;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isDelayed() {
        return delayed;
    }

    public String getDate() {
        return date;
    }

    public static final class Builder{
        private AcceptedDoseKey id;
        private Client client;
        private Cure cure;
        private boolean accepted;
        private boolean delayed;
        private String date;

        public Builder() {
        }

        public Builder(AcceptedDose copy){
            this.id = copy.getId();
            this.client = copy.getClient();
            this.cure = copy.getCure();
            this.date = copy.getDate();
            this.accepted = copy.isAccepted();
            this.delayed = copy.isDelayed();
        }

        public Builder id(AcceptedDoseKey id){
            this.id = id;
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

        public Builder date(String date){
            this.date = date;
            return this;
        }

        public Builder accepted(boolean accepted){
            this.accepted = accepted;
            return this;
        }

        public Builder delayed(boolean delayed){
            this.delayed = delayed;
            return this;
        }

        public AcceptedDose build(){
            return new AcceptedDose(this);
        }
    }
}
