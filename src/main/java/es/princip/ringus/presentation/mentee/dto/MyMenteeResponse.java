package es.princip.ringus.presentation.mentee.dto;

import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.presentation.common.dto.EducationResponse;

public record MyMenteeResponse(
        String nickname,
        EducationResponse education,
        String introduction,
        String imgUrl
) {
    public static MyMenteeResponse from(final Mentee mentee) {
        return new MyMenteeResponse(
                mentee.getNickname(),
                EducationResponse.from(mentee.getEducation()),
                mentee.getIntroduction(),
                mentee.getProfileImage().getFilePath()
        );
    }
}
