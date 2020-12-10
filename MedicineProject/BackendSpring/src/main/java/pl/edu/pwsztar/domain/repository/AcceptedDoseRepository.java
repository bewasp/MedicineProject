package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.entity.AcceptedDose;
import pl.edu.pwsztar.domain.entity.key.AcceptedDoseKey;

@Repository
public interface AcceptedDoseRepository extends JpaRepository<AcceptedDose, AcceptedDoseKey> , CrudRepository<AcceptedDose,AcceptedDoseKey> {
    @Query("SELECT dose FROM AcceptedDose dose WHERE dose.id.clientId = ?1 AND dose.id.cureId = ?2 AND SUBSTRING(dose.id.doseDate, 1 ,13)= ?3")
    AcceptedDose findInfo(Long clientId, Long cureId, String date);
}
