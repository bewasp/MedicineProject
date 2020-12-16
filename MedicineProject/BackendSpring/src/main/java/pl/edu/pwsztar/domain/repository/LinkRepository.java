package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pwsztar.domain.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Long>, CrudRepository<Link, Long> {
    @Query("SELECT link.id_client FROM Link link WHERE link.link = ?1")
    Long findClientIdByLink(String link);

    @Transactional
    @Modifying
    @Query("DELETE FROM Link link WHERE link.id_client=?1")
    void deleteEmailVerificationLink(Long userId);
}
