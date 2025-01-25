package es.princip.ringus.domain.email;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface EmailSessionRepository extends CrudRepository<EmailSession, String> {
    Optional<EmailSession> findByEmail(String email);
}