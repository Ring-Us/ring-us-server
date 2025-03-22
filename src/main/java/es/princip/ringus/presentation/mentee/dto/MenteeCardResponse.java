package es.princip.ringus.presentation.mentee.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.princip.ringus.infra.storage.domain.ProfileImage;

public record MenteeCardResponse(
    Long menteeId,
    String nickname,
    String imgUrl,
    @JsonInclude(NON_NULL) String status
) {
    public static MenteeCardResponse of(
        final Long menteeId,
        final String nickname,
        final ProfileImage profileImage,
        final String status
    ) {
        return new MenteeCardResponse(
            menteeId,
            nickname,
            profileImage.getFilePath(),
            status
        );
    }


    public static MenteeCardResponse of(
        final Long menteeId,
        final String nickname,
        final ProfileImage profileImage
    ) {
        return new MenteeCardResponse(
            menteeId,
            nickname,
            profileImage.getFilePath(),
            null
        );
    }
}
