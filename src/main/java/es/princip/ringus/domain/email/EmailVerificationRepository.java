package es.princip.ringus.domain.email;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface EmailVerificationRepository extends CrudRepository<EmailVerification, String> {
    Optional<EmailVerification> findByEmail(String email);
}