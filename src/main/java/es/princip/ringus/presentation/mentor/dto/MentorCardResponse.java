package es.princip.ringus.presentation.mentor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.princip.ringus.domain.mentor.vo.Introduction;
import es.princip.ringus.domain.mentor.vo.Organization;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import es.princip.ringus.presentation.common.dto.IntroductionResponse;
import es.princip.ringus.presentation.common.dto.OrganizationResponse;
import es.princip.ringus.presentation.common.dto.ProfileImageResponse;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

public record MentorCardResponse(
        Long mentorId,
        String nickname,
        ProfileImageResponse image,
        IntroductionResponse introduction,
        OrganizationResponse organization,
        String message,
        Long mentoringCount,
        @JsonInclude(NON_NULL) Boolean bookmarked,
        @JsonInclude(NON_NULL) String status
) {
    public static MentorCardResponse of(
            final Long mentorId,
            final String nickname,
            final ProfileImage profileImage,
            final Introduction introduction,
            final Organization organization,
            final String message,
            final String status,
            final Long mentoringCount
    ) {
        return new MentorCardResponse(
                mentorId,
                nickname,
                ProfileImageResponse.from(profileImage),
                IntroductionResponse.from(introduction),
                OrganizationResponse.from(organization),
                message,
                mentoringCount,
                null,
                status
        );
    }

    public static MentorCardResponse of(
            final Long mentorId,
            final String nickname,
            final ProfileImage profileImage,
            final Introduction introduction,
            final Organization organization,
            final String message,
            final Long mentoringCount,
            final Boolean bookmarked
    ) {
        return new MentorCardResponse(
                mentorId,
                nickname,
                ProfileImageResponse.from(profileImage),
                IntroductionResponse.from(introduction),
                OrganizationResponse.from(organization),
                message,
                mentoringCount,
                bookmarked,
                null
        );
    }
}
