package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.entity.ClientDose;
import pl.edu.pwsztar.domain.entity.key.ClientDoseKey;

@Repository
public interface ClientDoseRepository extends JpaRepository<ClientDose, ClientDoseKey>, CrudRepository<ClientDose,ClientDoseKey> {

}
