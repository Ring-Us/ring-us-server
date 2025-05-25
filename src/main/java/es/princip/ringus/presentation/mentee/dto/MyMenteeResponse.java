package es.princip.ringus.presentation.mentee.dto;

import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.presentation.common.dto.EducationResponse;
import es.princip.ringus.presentation.common.dto.ProfileImageResponse;

public record MyMenteeResponse(
        String email,
        String nickname,
        EducationResponse education,
        String introduction,
        ProfileImageResponse image
) {
    public static MyMenteeResponse from(
            final Member member,
            final Mentee mentee
    ) {
        return new MyMenteeResponse(
                member.getEmail(),
                mentee.getNickname(),
                EducationResponse.from(mentee.getEducation()),
                mentee.getIntroduction(),
                ProfileImageResponse.from(mentee.getProfileImage())
        );
    }
}
