package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.entity.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long> , CrudRepository<Client,Long> {
    @Query("SELECT client FROM Client client WHERE client.email=?1")
    Client findClientByEmail(String email);
}
