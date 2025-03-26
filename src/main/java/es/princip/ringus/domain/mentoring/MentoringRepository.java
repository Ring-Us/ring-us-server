package es.princip.ringus.domain.mentoring;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentoringRepository extends JpaRepository<Mentoring, Long> {
    Optional<Mentoring> findByMenteeIdAndMentorId(Long menteeId, Long mentorId);
}
