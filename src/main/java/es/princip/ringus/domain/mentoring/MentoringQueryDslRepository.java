package es.princip.ringus.domain.mentoring;

public interface MentoringQueryDslRepository {
    Long findMentoringCountBy(Long mentorId);
}
