package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pwsztar.domain.entity.Token;

public interface TokenRepository extends JpaRepository<Token,Long>, CrudRepository<Token,Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Token token WHERE token.userId=?1")
    void removeSession(Long userId);
}
