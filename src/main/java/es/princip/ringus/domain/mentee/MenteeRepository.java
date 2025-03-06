package es.princip.ringus.domain.mentee;

import es.princip.ringus.infra.storage.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    boolean existsByMemberId(Long memberId);

    @Query("SELECT m.profileImage FROM Mentee m WHERE m.memberId = :memberId")
    ProfileImage findProfileByMemberId(Long memberId);
}
