package es.princip.ringus.presentation.member.dto;

import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.presentation.common.dto.OrganizationResponse;
import es.princip.ringus.presentation.common.dto.ProfileImageResponse;

public record MentorProfileResponse(
        String nickname,
        ProfileImageResponse image,
        OrganizationResponse organization,
        Long mentoringCount
) {
    public static MentorProfileResponse from(final Mentor mentor, Long mentoringCount){
        return new MentorProfileResponse(
            mentor.getNickname(),
            ProfileImageResponse.from(mentor.getProfileImage()),
            OrganizationResponse.from(mentor.getOrganization()),
            mentoringCount
        );
    }
}