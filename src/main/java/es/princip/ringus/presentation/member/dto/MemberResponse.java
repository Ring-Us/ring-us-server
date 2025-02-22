package es.princip.ringus.presentation.member.dto;

import es.princip.ringus.domain.member.Member;

public record MemberResponse(
        Long id,
        String email,
        boolean isFileVerified,
        boolean isProfileRegisterd,
        boolean isUniversityVerified
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getEmail(),
                member.isProfileRegistered(),
                member.isProfileRegistered(),
                member.isUniversityVerified()
        );
    }
}