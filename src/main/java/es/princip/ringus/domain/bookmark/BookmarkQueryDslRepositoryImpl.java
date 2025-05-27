package es.princip.ringus.domain.bookmark;

import es.princip.ringus.domain.support.QueryDslSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static es.princip.ringus.domain.bookmark.QBookmark.bookmark;

@Repository
@RequiredArgsConstructor
public class BookmarkQueryDslRepositoryImpl extends QueryDslSupport implements BookmarkQueryDslRepository {
    @Override
    public Boolean isBookmarked(Long memberId, Long mentorId) {
        return queryFactory.select()
            .from(bookmark)
            .where(bookmark.mentor.id.eq(mentorId).and(bookmark.mentee.memberId.eq(memberId)))
            .fetchCount() > 0;
    }
}
