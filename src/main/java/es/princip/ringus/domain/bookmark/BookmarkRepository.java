package es.princip.ringus.domain.bookmark;

import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentor.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkQueryDslRepository {
    Optional<Bookmark> findByMentorAndMentee(Mentor mentor, Mentee mentee);

    Optional<Bookmark> findByMentee(Mentee mentee);
}