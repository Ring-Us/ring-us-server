package es.princip.ringus.domain.mentee;

import es.princip.ringus.domain.common.Education;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Long>, MenteeQueryDslRepository {
    Optional<Mentee> findByMemberId(Long memberId);
    boolean existsByMemberId(Long memberId);

    @Query("SELECT m.profileImage FROM Mentee m WHERE m.memberId = :memberId")
    ProfileImage findProfileByMemberId(Long memberId);

    @Query("SELECT m.education FROM Mentee m WHERE m.memberId = :memberId")
    Education findEducationByMemberId(Long memberId);

    boolean existsByNickname(String nickname);
}
