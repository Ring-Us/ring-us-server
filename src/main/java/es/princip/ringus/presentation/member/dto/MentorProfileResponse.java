package es.princip.ringus.presentation.member.dto;

import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.presentation.common.dto.OrganizationResponse;

public record MentorProfileResponse(
        String nickname,
        String imgUrl,
        OrganizationResponse organization
) {
    public static MentorProfileResponse from(final Mentor mentor){
        return new MentorProfileResponse(mentor.getNickname(), mentor.getProfileImage().getFilePath(), OrganizationResponse.from(mentor.getOrganization()));
    }
}