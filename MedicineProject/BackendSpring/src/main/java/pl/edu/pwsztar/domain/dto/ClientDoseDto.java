package pl.edu.pwsztar.domain.dto;


import java.io.Serializable;


public class ClientDoseDto implements Serializable {
    private String name;
    private Integer dailyDose;
    private Integer doseTimestamp;
    private Integer doseNumber;

    public ClientDoseDto() {

    }

    private ClientDoseDto(Builder builder) {
        this.name = builder.name;
        this.dailyDose = builder.dailyDose;
        this.doseTimestamp = builder.doseTimestamp;
        this.doseNumber = builder.doseNumber;
    }

    public String getName() {
        return name;
    }

    public Integer getDailyDose() {
        return dailyDose;
    }

    public Integer getDoseTimestamp() {
        return doseTimestamp;
    }

    public Integer getDoseNumber() {
        return doseNumber;
    }

    public static final class Builder{
        private String name;
        private Integer dailyDose;
        private Integer doseTimestamp;
        private Integer doseNumber;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder dailyDose(Integer dailyDose){
            this.dailyDose = dailyDose;
            return this;
        }

        public Builder doseTimestamp(Integer doseTimestamp){
            this.doseTimestamp = doseTimestamp;
            return this;
        }

        public Builder doseNumber(Integer doseNumber){
            this.doseNumber = doseNumber;
            return this;
        }

        public ClientDoseDto build(){
            return new ClientDoseDto(this);
        }
    }
}
