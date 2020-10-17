package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.entity.Cure;

@Repository
public interface CureRepository extends JpaRepository<Cure,Long> , CrudRepository<Cure,Long> {
    @Query("SELECT cure FROM Cure cure WHERE cure.name=?1 AND cure.dailyDose = ?2 AND cure.doseNumber = ?3 AND cure.doseTimestamp = ?4")
    Cure findCure(String name, Integer dailyDose, Integer doseNumber, Integer doseTimestamp);
}
