package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.vo.Hashtag;
import es.princip.ringus.domain.mentor.vo.MentoringField;
import es.princip.ringus.presentation.common.dto.*;

import java.util.List;

public record MyMentorResponse(
    String email,
    String nickname,
    EducationResponse education,
    OrganizationResponse organization,
    IntroductionResponse introduction,
    TimezoneResponse timezone,
    List<String> mentoringField,
    List<String> hashtags,
    String message,
    PortfolioResponse portfolio,
    Long mentoringCount
) {
    public static MyMentorResponse from(final Member member, final Mentor mentor, Long mentoringCount) {
        return new MyMentorResponse(
                member.getEmail(),
                mentor.getNickname(),
                EducationResponse.from(mentor.getEducation()),
                OrganizationResponse.from(mentor.getOrganization()),
                IntroductionResponse.from(mentor.getIntroduction()),
                TimezoneResponse.from(mentor.getTimezone()),
                mentor.getMentoringField().stream().map(MentoringField::getKor).toList(),
                mentor.getHashtags().stream().map(Hashtag::getValue).toList(),
                mentor.getMessage(),
                PortfolioResponse.from(mentor.getPortfolio()),
                mentoringCount
        );
    }
}
