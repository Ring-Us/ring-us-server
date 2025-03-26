package es.princip.ringus.presentation.member.dto;

import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.presentation.common.dto.EducationResponse;
import es.princip.ringus.presentation.common.dto.ProfileImageResponse;

public record MenteeProfileResponse(
        String nickname,
        ProfileImageResponse image,
        EducationResponse education
) {
    public static MenteeProfileResponse from(final Mentee mentee) {
        return new MenteeProfileResponse(mentee.getNickname(), ProfileImageResponse.from(mentee.getProfileImage()), EducationResponse.from(mentee.getEducation()));
    }
}