package weteam.backend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weteam.backend.auth.domain.Auth;

import javax.swing.text.DefaultStyledDocument;
import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth,Long> {
    Optional<Auth> findByUid(String uid);
}
