package pl.edu.pwsztar.domain.dao;

import java.io.Serializable;

public class ClientDao implements Serializable {
    private Long clientId;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String password;
    private Boolean activatedEmail;


    public ClientDao() {
    }

    private ClientDao(Builder builder) {
        this.clientId = builder.clientId;
        this.email = builder.email;
        this.name = builder.name;
        this.surname = builder.surname;
        this.phoneNumber = builder.phoneNumber;
        this.password = builder.password;
        this.activatedEmail = builder.activatedEmail;
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
        private String email;
        private String name;
        private String surname;
        private String phoneNumber;
        private String password;
        private Boolean activatedEmail;

        public Builder(){

        }

        public Builder(ClientDao copy) {
            this.clientId = copy.getClientId();
            this.email = copy.getEmail();
            this.name = copy.getName();
            this.surname = copy.getSurname();
            this.phoneNumber = copy.getPhoneNumber();
            this.password = copy.getPassword();
            this.activatedEmail = copy.getActivatedEmail();
        }

        public Builder clientId(Long clientId){
            this.clientId = clientId;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }

        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder activatedEmail(Boolean activatedEmail){
            this.activatedEmail = activatedEmail;
            return this;
        }

        public ClientDao build(){
            return new ClientDao(this);
        }
    }
}
