package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.infra.storage.domain.ProfileImage;

public record ProfileImageResponse(
        String fileName,
        String filePath
) {
    public static ProfileImageResponse from(final ProfileImage profileImage) {
        return new ProfileImageResponse(
            profileImage.getFileName(),
            profileImage.getFilePath()
        );
    }
}