package pl.edu.pwsztar.domain.dto.cure;

import java.io.Serializable;

public class ClientDoseInfoDto implements Serializable {
    private Integer acceptedDose;
    private Integer declinedDose;
    private Integer delayedDose;

    public ClientDoseInfoDto() {
    }

    private ClientDoseInfoDto(Builder builder) {
        this.acceptedDose = builder.acceptedDose;
        this.declinedDose = builder.declinedDose;
        this.delayedDose = builder.delayedDose;
    }

    public Integer getAcceptedDose() {
        return acceptedDose;
    }

    public Integer getDeclinedDose() {
        return declinedDose;
    }

    public Integer getDelayedDose() {
        return delayedDose;
    }

    public static final class Builder{
        private Integer acceptedDose;
        private Integer declinedDose;
        private Integer delayedDose;

        public Builder() {
        }

        public Builder(ClientDoseInfoDto copy) {
            this.acceptedDose = copy.getAcceptedDose();
            this.declinedDose = copy.getDeclinedDose();
            this.delayedDose = copy.getDelayedDose();
        }

        public Builder acceptedDose(Integer acceptedDose){
            this.acceptedDose = acceptedDose;
            return this;
        }

        public Builder declinedDose(Integer declinedDose){
            this.declinedDose = declinedDose;
            return this;
        }

        public Builder delayedDose(Integer delayedDose){
            this.delayedDose = delayedDose;
            return this;
        }

        public ClientDoseInfoDto build(){
            return new ClientDoseInfoDto(this);
        }
    }
}
