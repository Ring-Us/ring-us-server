package es.princip.ringus.presentation.mentoring.dto;

import es.princip.ringus.domain.mentoring.Mentoring;
import es.princip.ringus.domain.mentoring.MentoringStatus;

public record MentoringRejectResponse(
        Long mentoringId,
        MentoringStatus status
) {
    public static MentoringRejectResponse from(Mentoring mentoring) {
        return new MentoringRejectResponse(
                mentoring.getId(),
                mentoring.getMentoringStatus()
        );
    }
}
