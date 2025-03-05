package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.domain.mentor.vo.Introduction;
import es.princip.ringus.domain.mentor.vo.Organization;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import es.princip.ringus.presentation.common.dto.IntroductionResponse;
import es.princip.ringus.presentation.common.dto.OrganizationResponse;

public record MentorCardResponse(
        Long mentorId,
        String nickname,
        String imgUrl,
        IntroductionResponse introduction,
        OrganizationResponse organization,
        String message,
        int mentoringCount
) {
    public static MentorCardResponse of(
            final Long mentorId,
            final String nickname,
            final ProfileImage profileImage,
            final Introduction introduction,
            final Organization organization,
            final String message,
            final int mentoringCount
    ) {
        return new MentorCardResponse(
                mentorId,
                nickname,
                profileImage.getFilePath(),
                IntroductionResponse.of(introduction),
                OrganizationResponse.of(organization),
                message,
                mentoringCount
        );
    }
}
