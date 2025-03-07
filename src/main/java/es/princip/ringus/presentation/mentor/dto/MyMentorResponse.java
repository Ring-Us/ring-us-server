package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.presentation.common.dto.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record MyMentorResponse(
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
    public static MyMentorResponse from(final Mentor mentor) {
        return new MyMentorResponse(
                mentor.getNickname(),
                EducationResponse.from(mentor.getEducation()),
                OrganizationResponse.of(mentor.getOrganization()),
                IntroductionResponse.of(mentor.getIntroduction()),
                TimezoneResponse.of(mentor.getTimezone()),
                mentor.getMentoringField().stream().map(String::valueOf).collect(Collectors.toSet()),
                mentor.getHashtags().stream().map(String::valueOf).toList(),
                mentor.getMessage(),
                PortfolioResponse.of(mentor.getPortfolio())
        );
    }
}
