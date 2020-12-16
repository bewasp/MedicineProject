package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pwsztar.domain.entity.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long> , CrudRepository<Client,Long> {
    @Query("SELECT client FROM Client client WHERE client.email=?1")
    Client findClientByEmail(String email);

    @Query("SELECT client FROM Client client WHERE client.clientId = ?1")
    Client findClient(Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Client SET activatedEmail = true WHERE clientId = ?1")
    void verifyClientEmail(Long clientId);
}
