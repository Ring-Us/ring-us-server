package es.princip.ringus.presentation.mentoring.dto;

import es.princip.ringus.domain.mentoring.MentoringTime;
import jakarta.validation.constraints.NotNull;

public record AcceptMentoringRequest(
        @NotNull Long menteeId,
        MentoringTime mentoringTime
) {
}
