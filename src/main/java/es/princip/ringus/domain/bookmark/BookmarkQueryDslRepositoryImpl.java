package es.princip.ringus.domain.bookmark;

import es.princip.ringus.domain.support.QueryDslSupport;

import static es.princip.ringus.domain.mentoring.QMentoring.mentoring;

public class BookmarkQueryDslRepositoryImpl extends QueryDslSupport implements BookmarkQueryDslRepository {
    @Override
    public Boolean isBookmarked(Long memberId, Long mentorId) {
        return queryFactory.select()
            .from(mentoring)
            .where(mentoring.mentor.id.eq(mentorId).and(mentoring.mentee.memberId.eq(memberId)))
            .fetchCount() > 0;
    }
}
