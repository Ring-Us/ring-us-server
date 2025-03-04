package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.presentation.common.dto.EducationRequest;
import es.princip.ringus.presentation.common.dto.IntroductionRequest;
import es.princip.ringus.presentation.common.dto.OrganizationRequest;
import es.princip.ringus.presentation.common.dto.PortfolioRequest;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;

public record EditMentorRequest(
        @NotBlank Long mentorId,
        @NotBlank String nickname,
        IntroductionRequest introduction,
        Set<String> mentoringField,
        EducationRequest education,
        OrganizationRequest organization,
        TimezoneRequest timezone,
        List<String> hashtags,
        String message,
        PortfolioRequest portfolio
) {
}