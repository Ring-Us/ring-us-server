package es.princip.ringus.domain.mentoring;

import es.princip.ringus.domain.support.QueryDslSupport;

import static es.princip.ringus.domain.mentoring.QMentoring.mentoring;

public class MentoringQueryDslRepositoryImpl extends QueryDslSupport implements MentoringQueryDslRepository {

    @Override
    public Long findMentoringCountBy(Long mentorId) {
        return  queryFactory.select()
            .from(mentoring)
            .where(mentoring.mentor.id.eq(mentorId).and(mentoring.mentoringStatus.eq(MentoringStatus.COMPLETED)))
            .fetchCount();
    }
}
