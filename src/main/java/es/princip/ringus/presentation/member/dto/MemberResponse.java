package es.princip.ringus.presentation.member.dto;

import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberType;

public record MemberResponse(
        MemberType memberType,
        String email,
        boolean isFileVerified,
        boolean isProfileRegisterd,
        boolean isUniversityVerified
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getMemberType(),
                member.getEmail(),
                member.isProfileRegistered(),
                member.isProfileRegistered(),
                member.isUniversityVerified()
        );
    }
}