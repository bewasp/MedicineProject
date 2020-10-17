package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.entity.AcceptedDose;
import pl.edu.pwsztar.domain.entity.key.AcceptedDoseKey;

@Repository
public interface AcceptedDoseRepository extends JpaRepository<AcceptedDose, AcceptedDoseKey> , CrudRepository<AcceptedDose,AcceptedDoseKey> {

}
