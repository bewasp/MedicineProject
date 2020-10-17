package pl.edu.pwsztar.domain.dto;

import pl.edu.pwsztar.domain.entity.ClientDose;

import java.io.Serializable;
import java.util.Set;

public class CureDto implements Serializable {
    private String name;
    private String type;

    public CureDto() {
    }

    private CureDto(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public static final class Builder{
        private String name;
        private String type;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder type(String type){
            this.type = type;
            return this;
        }

        public CureDto build(){
            return new CureDto(this);
        }
    }
}
