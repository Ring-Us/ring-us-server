package es.princip.ringus.presentation.mentoring.dto;

import jakarta.validation.constraints.NotNull;

public record RejectMentoringRequest(
        @NotNull
        Long mentoringId
) {
}
