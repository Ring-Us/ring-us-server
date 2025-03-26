package es.princip.ringus.presentation.mentee.dto;

import es.princip.ringus.presentation.common.dto.EducationRequest;
import es.princip.ringus.presentation.common.dto.ProfileImageRequest;
import jakarta.validation.constraints.NotBlank;

public record EditMenteeRequest(
        @NotBlank String nickname,
        EducationRequest education,
        ProfileImageRequest image,
        @NotBlank String introduction
) {
}