package es.princip.ringus.domain.mentor;

import es.princip.ringus.domain.mentor.vo.Organization;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long>, MentorQueryDslRepository  {
    boolean existsByMemberId(Long memberId);

    @Query("SELECT m.profileImage FROM Mentor m WHERE m.memberId = :memberId")
    ProfileImage findProfileByMemberId(Long memberId);

    @Query("SELECT m.organization FROM Mentor m WHERE m.memberId = :memberId")
    Organization findOrganizationByMemberId(Long memberId);

    @Query("SELECT m FROM Mentor m WHERE m.memberId = :memberId")
    Optional<Mentor> findByMemberId(Long memberId);
  
    boolean existsByNickname(String nickname);
}