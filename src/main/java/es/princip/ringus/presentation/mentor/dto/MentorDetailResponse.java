package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.presentation.common.dto.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record MentorDetailResponse(
        String nickname,
        EducationResponse education,
        OrganizationResponse organization,
        IntroductionResponse introduction,
        TimezoneResponse timezone,
        Set<String> mentoringField,
        List<String> hashtags,
        String message,
        PortfolioResponse portfolio
) {
    public static MentorDetailResponse from(final Mentor mentor) {
        return new MentorDetailResponse(
                mentor.getNickname(),
                EducationResponse.from(mentor.getEducation()),
                OrganizationResponse.from(mentor.getOrganization()),
                IntroductionResponse.from(mentor.getIntroduction()),
                TimezoneResponse.from(mentor.getTimezone()),
                mentor.getMentoringField().stream().map(String::valueOf).collect(Collectors.toSet()),
                mentor.getHashtags().stream().map(String::valueOf).toList(),
                mentor.getMessage(),
                PortfolioResponse.from(mentor.getPortfolio())
        );
    }
}
