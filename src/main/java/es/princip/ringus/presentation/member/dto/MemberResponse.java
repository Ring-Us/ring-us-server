package es.princip.ringus.presentation.member.dto;

import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberType;

public record MemberResponse(
        Long memberId,
        MemberType memberType,
        boolean isFileVerified,
        boolean isProfileRegisterd,
        boolean isUniversityVerified,
        String imgUrl
) {
    public static MemberResponse of(Member member, String imgUrl) {
        return new MemberResponse(
                member.getId(),
                member.getMemberType(),
                member.isProfileRegistered(),
                member.isProfileRegistered(),
                member.isUniversityVerified(),
                imgUrl
        );
    }
}