package es.princip.ringus.presentation.mentee.dto;

import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.presentation.common.dto.EducationResponse;

public record MenteeDetailResponse(
        String nickname,
        EducationResponse education,
        String introduction
) {
    public static MenteeDetailResponse from(final Mentee mentee) {
        return new MenteeDetailResponse(
                mentee.getNickname(),
                EducationResponse.from(mentee.getEducation()),
                mentee.getIntroduction()
        );
    }
}
