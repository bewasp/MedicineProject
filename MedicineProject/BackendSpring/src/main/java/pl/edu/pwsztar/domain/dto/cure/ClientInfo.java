package pl.edu.pwsztar.domain.dto.cure;

import java.util.List;

public class ClientInfo {
    private ClientDoseInfoDto info;
    private List<ClientDoseReportDto> report;

    public ClientInfo() {
    }

    public ClientInfo(Builder builder) {
        this.info = builder.info;
        this.report = builder.report;
    }

    public ClientDoseInfoDto getInfo() {
        return info;
    }

    public List<ClientDoseReportDto> getReport() {
        return report;
    }

    public static final class Builder{
        private ClientDoseInfoDto info;
        private List<ClientDoseReportDto> report;

        public Builder info(ClientDoseInfoDto info){
            this.info = info;
            return this;
        }

        public Builder report(List<ClientDoseReportDto> report){
            this.report = report;
            return this;
        }

        public ClientInfo build(){
            return new ClientInfo(this);
        }
    }
}
