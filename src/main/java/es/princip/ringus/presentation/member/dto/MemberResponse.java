package es.princip.ringus.presentation.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.princip.ringus.domain.common.Education;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberType;
import es.princip.ringus.domain.mentor.vo.Organization;
import es.princip.ringus.presentation.common.dto.EducationResponse;
import es.princip.ringus.presentation.common.dto.OrganizationResponse;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public record MemberResponse(
        Long memberId,
        MemberType memberType,
        boolean isFileVerified,
        boolean isProfileRegisterd,
        boolean isUniversityVerified,
        @JsonInclude(NON_NULL) MenteeProfileResponse menteeProfile,
        @JsonInclude(NON_NULL) MentorProfileResponse mentorProfile
) {
    public static MemberResponse of(final Member member) {
        return new MemberResponse(
                member.getId(),
                member.getMemberType(),
                member.isProfileRegistered(),
                member.isProfileRegistered(),
                member.isUniversityVerified(),
                null,
                null
        );
    }
    public static MemberResponse of(final Member member, final MenteeProfileResponse profile) {
        return new MemberResponse(
                member.getId(),
                member.getMemberType(),
                member.isProfileRegistered(),
                member.isProfileRegistered(),
                member.isUniversityVerified(),
                profile,
                null
        );
    }
    public static MemberResponse of(final Member member, final MentorProfileResponse profile) {
        return new MemberResponse(
                member.getId(),
                member.getMemberType(),
                member.isProfileRegistered(),
                member.isProfileRegistered(),
                member.isUniversityVerified(),
                null,
                profile
        );
    }
}