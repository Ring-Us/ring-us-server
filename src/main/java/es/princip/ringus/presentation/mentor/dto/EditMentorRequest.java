package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.presentation.common.dto.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public record EditMentorRequest(
        @NotBlank String nickname,
        IntroductionRequest introduction,
        @UniqueElements List<String> mentoringField,
        EducationRequest education,
        OrganizationRequest organization,
        TimezoneRequest timezone,
        List<String> hashtags,
        String message,
        PortfolioRequest portfolio,
        ProfileImageRequest image
) {
}