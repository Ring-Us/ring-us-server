package es.princip.ringus.domain.mentoring.accept;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentoringAcceptRepository extends JpaRepository<MentoringAccept, Long> {
}
