package es.princip.ringus.presentation.mentoring.dto;

import jakarta.validation.constraints.NotNull;

public record CancelMentoringRequest(
        @NotNull
        Long mentoringId
) {
}
