package es.princip.ringus.presentation.member.dto;

import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.presentation.common.dto.OrganizationResponse;
import es.princip.ringus.presentation.common.dto.ProfileImageResponse;

public record MentorProfileResponse(
        String nickname,
        ProfileImageResponse image,
        OrganizationResponse organization,
        int mentoringCount
) {
    public static MentorProfileResponse from(final Mentor mentor){
        return new MentorProfileResponse(mentor.getNickname(), ProfileImageResponse.from(mentor.getProfileImage()), OrganizationResponse.from(mentor.getOrganization()),0);
    }
}