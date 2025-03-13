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
        @JsonInclude(NON_NULL) String imgUrl,
        @JsonInclude(NON_NULL) EducationResponse education,
        @JsonInclude(NON_NULL) OrganizationResponse organization
) {
    public static MemberResponse of(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getMemberType(),
                member.isProfileRegistered(),
                member.isProfileRegistered(),
                member.isUniversityVerified(),
                null,
                null,
                null
        );
    }
    public static MemberResponse of(Member member, String imgUrl, Education education) {
        return new MemberResponse(
                member.getId(),
                member.getMemberType(),
                member.isProfileRegistered(),
                member.isProfileRegistered(),
                member.isUniversityVerified(),
                imgUrl,
                EducationResponse.from(education),
                null
        );
    }
    public static MemberResponse of(Member member, String imgUrl, Organization organization) {
        return new MemberResponse(
                member.getId(),
                member.getMemberType(),
                member.isProfileRegistered(),
                member.isProfileRegistered(),
                member.isUniversityVerified(),
                imgUrl,
                null,
                OrganizationResponse.from(organization)
        );
    }
}