package es.princip.ringus.presentation.member.dto;

import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.presentation.common.dto.EducationResponse;

public record MenteeProfileResponse(
        String nickname,
        String imgUrl,
        EducationResponse education
) {
    public static MenteeProfileResponse from(final Mentee mentee) {
        return new MenteeProfileResponse(mentee.getNickname(), mentee.getProfileImage().getFilePath(), EducationResponse.from(mentee.getEducation()));
    }
}