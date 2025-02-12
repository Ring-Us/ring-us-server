package es.princip.ringus.domain.mentee;

import es.princip.ringus.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, String> {
    boolean existsByMember(Member member);
}
