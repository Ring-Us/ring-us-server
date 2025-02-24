package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.domain.common.Education;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.vo.*;
import es.princip.ringus.presentation.common.dto.EducationRequest;
import es.princip.ringus.presentation.common.dto.OrganizationRequest;
import es.princip.ringus.presentation.common.dto.PortfolioRequest;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record MentorRequest(
        @NotBlank String email,
        @NotBlank String nickname,
        @NotBlank String introduction,
        Set<String> mentoringField,
        EducationRequest education,
        OrganizationRequest organization,
        TimezoneRequest timezone,
        List<String> hashtags,
        String message,
        PortfolioRequest portfolio
) {
    public Mentor toEntity(Long memberId) {
        return Mentor.builder()
                .nickname(nickname)
                .education(new Education(education().schoolName(), education().major()))
                .organization(new Organization(organization().name(), organization().role(), organization().experience()))
                .introduction(introduction)
                .timezone(new Timezone(timezone().startTime(), timezone().endTime()))
                .mentoringField(mentoringField.stream().map(MentoringField::valueOf).collect(Collectors.toSet()))
                .hashtags(hashtags.stream().map(Hashtag::new).toList())
                .message(message)
                .portfolio(new Portfolio(portfolio.url(), portfolio.description()))
                .memberId(memberId)
                .build();
    }
}