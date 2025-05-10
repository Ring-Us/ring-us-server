package es.princip.ringus.presentation.mentoring.dto;

import es.princip.ringus.domain.mentoring.Mentoring;
import es.princip.ringus.domain.mentoring.MentoringStatus;

public record MentoringCancelResponse(
        Long mentoringId,
        MentoringStatus status
) {
    public static MentoringCancelResponse from(Mentoring mentoring) {
        return new MentoringCancelResponse(
                mentoring.getId(),
                mentoring.getMentoringStatus()
        );
    }
}
