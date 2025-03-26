package es.princip.ringus.domain.bookmark;

public interface BookmarkQueryDslRepository {
    Boolean isBookmarked(Long memberId, Long mentorId);
}
