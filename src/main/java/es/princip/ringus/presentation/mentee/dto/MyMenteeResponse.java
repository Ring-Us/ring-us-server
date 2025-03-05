package es.princip.ringus.presentation.mentee.dto;

import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.presentation.common.dto.EducationResponse;

public record MyMenteeResponse(
        String nickname,
        EducationResponse education,
        String introduction
) {
    public static MyMenteeResponse of(final Mentee mentee) {
        return new MyMenteeResponse(
                mentee.getNickname(),
                EducationResponse.of(mentee.getEducation()),
                mentee.getIntroduction()
        );
    }
}
