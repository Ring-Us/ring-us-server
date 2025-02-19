package es.princip.ringus.infra.storage.dto;

import es.princip.ringus.domain.user.UserType;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import org.springframework.web.multipart.MultipartFile;

public record ProfileUploadRequest(
        MultipartFile file,
        UserType userType,
        Long userId  // 업로드 요청 시 사용자 ID도 함께 받음
) {
    public static ProfileImage toEntity(ProfileUploadRequest request, String filePath) {
        return ProfileImage.builder()
                .fileName(request.file.getOriginalFilename())
                .filePath(filePath)
                .userType(request.userType())
                .build();
    }
}
