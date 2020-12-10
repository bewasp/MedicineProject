package pl.edu.pwsztar.domain.dao;

import java.io.Serializable;

public class CureDao implements Serializable {
    private Long cureId;
    private String name;
    private Integer dailyDose;
    private Integer doseTimestamp;
    private Integer doseNumber;

    public CureDao() {
    }

    public CureDao(Builder builder) {
        this.cureId = builder.cureId;
        this.name = builder.name;
        this.dailyDose = builder.dailyDose;
        this.doseTimestamp = builder.doseTimestamp;
        this.doseNumber = builder.doseNumber;
    }

    public Long getCureId() {
        return cureId;
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
        private Long cureId;
        private String name;
        private Integer dailyDose;
        private Integer doseTimestamp;
        private Integer doseNumber;

        public Builder cureId(Long cureId){
            this.cureId = cureId;
            return this;
        }

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


        public CureDao build(){
            return new CureDao(this);
        }
    }
}
