package es.princip.ringus.presentation.user.dto;

import jakarta.validation.constraints.NotBlank;

public record MentorRequest(
        @NotBlank
        String email,
        @NotBlank
        String nickname,
        @NotBlank
        String company,
        @NotBlank
        String job,
        int experience,
        @NotBlank
        String introduction
) {
}
