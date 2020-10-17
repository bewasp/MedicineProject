package pl.edu.pwsztar.domain.entity.key;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AcceptedDoseKey implements Serializable {
    @Column(name = "id_client")
    private Long clientId;

    @Column(name = "id_cure")
    private Long cureId;

    private String doseDate;

    public AcceptedDoseKey() {
    }

    public AcceptedDoseKey(Long clientId, Long cureId, String doseDate) {
        this.clientId = clientId;
        this.cureId = cureId;
        this.doseDate = doseDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getCureId() {
        return cureId;
    }

    public String getDate() {
        return doseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptedDoseKey that = (AcceptedDoseKey) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(cureId, that.cureId) &&
                Objects.equals(doseDate, that.doseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, cureId, doseDate);
    }
}
