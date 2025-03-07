package es.princip.ringus.domain.mentee;

import es.princip.ringus.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    Optional<Mentee> findByMemberId(Long memberId);
}
