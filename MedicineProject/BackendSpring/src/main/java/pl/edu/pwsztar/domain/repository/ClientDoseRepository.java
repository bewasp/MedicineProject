package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.entity.ClientDose;
import pl.edu.pwsztar.domain.entity.Cure;
import pl.edu.pwsztar.domain.entity.key.ClientDoseKey;

import java.util.List;

@Repository
public interface ClientDoseRepository extends JpaRepository<ClientDose, ClientDoseKey>, CrudRepository<ClientDose,ClientDoseKey> {
    @Query("SELECT client_dose.cure FROM ClientDose client_dose WHERE client_dose.client.clientId = ?1")
    List<Cure> getAllCure(Long clientId);
}
