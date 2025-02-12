package es.princip.ringus.presentation.user.dto;

import es.princip.ringus.domain.mentee.EducationLevelType;
import jakarta.validation.constraints.NotBlank;

public record MenteeRequest(
        @NotBlank
        String email,

        @NotBlank
        String nickname,

        @NotBlank
        String major,

        EducationLevelType educationLevelType,

        @NotBlank
        String introduction
) {}
