package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.vo.Hashtag;
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
    PortfolioResponse portfolio,
    Long mentoringCount
) {
    public static MyMentorResponse from(final Mentor mentor, Long mentoringCount) {
        return new MyMentorResponse(
                mentor.getNickname(),
                EducationResponse.from(mentor.getEducation()),
                OrganizationResponse.from(mentor.getOrganization()),
                IntroductionResponse.from(mentor.getIntroduction()),
                TimezoneResponse.from(mentor.getTimezone()),
                mentor.getMentoringField().stream().map(String::valueOf).collect(Collectors.toSet()),
                mentor.getHashtags().stream().map(Hashtag::getValue).toList(),
                mentor.getMessage(),
                PortfolioResponse.from(mentor.getPortfolio()),
                mentoringCount
        );
    }
}
