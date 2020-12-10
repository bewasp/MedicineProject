package pl.edu.pwsztar.domain.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="client_personal"  , schema = "medicine")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long clientId;

    @Column(name="email")
    private String email;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="password")
    private String password;

    @Column(name="activated_email")
    private Boolean activatedEmail;

    public Client() {
    }

    public Client(Builder builder) {
        this.email = builder.email;
        this.name = builder.name;
        this.surname = builder.surname;
        this.phoneNumber = builder.phoneNumber;
        this.password = builder.password;
        this.acceptedDoses = builder.acceptedDoses;
        this.dose = builder.dose;
        this.activatedEmail = builder.activatedEmail;
    }

    @OneToMany(mappedBy = "client")
    private List<AcceptedDose> acceptedDoses;

    @OneToMany(mappedBy = "client")
    private List<ClientDose> dose;

    @OneToMany(mappedBy = "client")
    private List<Token> tokens;

    @OneToOne(mappedBy = "client")
    private Link link;

    public List<AcceptedDose> getAcceptedDoses() {
        return acceptedDoses;
    }

    public List<ClientDose> getDose() {
        return dose;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActivatedEmail() {
        return activatedEmail;
    }

    public static final class Builder{
        private Long clientId;
        private List<AcceptedDose> acceptedDoses;
        private List<ClientDose> dose;
        private String email;
        private String name;
        private String surname;
        private String phoneNumber;
        private String password;
        private Boolean activatedEmail;

        public Builder() {
        }

        public Builder(Client copy){
            this.clientId = copy.getClientId();
            this.email = copy.getEmail();
            this.name = copy.getName();
            this.surname = copy.getSurname();
            this.phoneNumber = copy.getPhoneNumber();
            this.password =copy.getPassword();
            this.acceptedDoses = copy.getAcceptedDoses();
            this.dose = copy.getDose();
            this.activatedEmail = copy.getActivatedEmail();
        }

        public Builder clientId(Long clientId){
            this.clientId=clientId;
            return this;
        }

        public Builder acceptedDoses(List<AcceptedDose> acceptedDoses){
            this.acceptedDoses=acceptedDoses;
            return this;
        }

        public Builder dose(List<ClientDose> dose){
            this.dose=dose;
            return this;
        }

        public Builder email(String email){
            this.email=email;
            return this;
        }

        public Builder name(String name){
            this.name=name;
            return this;
        }

        public Builder surname(String surname){
            this.surname=surname;
            return this;
        }

        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber=phoneNumber;
            return this;
        }

        public Builder password(String password){
            this.password=password;
            return this;
        }

        public Builder activatedEmail(Boolean activatedEmail){
            this.activatedEmail = activatedEmail;
            return this;
        }

        public Client build(){
            return new Client(this);
        }
    }
}
