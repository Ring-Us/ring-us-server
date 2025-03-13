package es.princip.ringus.application.member.service;

import es.princip.ringus.domain.mentor.vo.Organization;
import es.princip.ringus.infra.storage.domain.ProfileImage;

public record MemberMentorProfile(
        ProfileImage profileImage,
        Organization organization
) {
}
