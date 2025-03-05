package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.domain.common.Education;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.vo.Hashtag;
import es.princip.ringus.domain.mentor.vo.Introduction;
import es.princip.ringus.domain.mentor.vo.MentoringField;
import es.princip.ringus.presentation.common.dto.EducationRequest;
import es.princip.ringus.presentation.common.dto.OrganizationRequest;
import es.princip.ringus.presentation.common.dto.PortfolioRequest;
import es.princip.ringus.presentation.common.dto.ProfileImageRequest;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record MentorRequest(
        @NotBlank String nickname,
        Introduction introduction,
        Set<String> mentoringField,
        EducationRequest education,
        OrganizationRequest organization,
        TimezoneRequest timezone,
        List<String> hashtags,
        String message,
        PortfolioRequest portfolio,
        ProfileImageRequest image
) {
    public Mentor toEntity(Long memberId) {
        return Mentor.builder()
                .nickname(nickname)
                .education(new Education(education().schoolName(), education().major()))
                .organization(organization.toEntity())
                .introduction(introduction)
                .timezone(timezone.toEntity())
                .mentoringField(mentoringField.stream().map(MentoringField::valueOf).collect(Collectors.toSet()))
                .hashtags(hashtags.stream().map(Hashtag::new).toList())
                .message(message)
                .portfolio(portfolio.toEntity())
                .profileImage(image.toEntity())
                .memberId(memberId)
                .build();
    }
}