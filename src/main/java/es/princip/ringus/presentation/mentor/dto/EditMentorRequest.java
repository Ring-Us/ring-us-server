package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.presentation.common.dto.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;

public record EditMentorRequest(
        @NotBlank String nickname,
        IntroductionRequest introduction,
        Set<String> mentoringField,
        EducationRequest education,
        OrganizationRequest organization,
        TimezoneRequest timezone,
        List<String> hashtags,
        String message,
        PortfolioRequest portfolio,
        ProfileImageRequest image
) {
}