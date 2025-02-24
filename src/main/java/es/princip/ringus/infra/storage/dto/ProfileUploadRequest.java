package es.princip.ringus.infra.storage.dto;

import es.princip.ringus.domain.member.MemberType;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import org.springframework.web.multipart.MultipartFile;

public record ProfileUploadRequest(
        MultipartFile file,
        MemberType memberType,
        Long userId  // 업로드 요청 시 사용자 ID도 함께 받음
) {
    // 기본생성자 명시적 정의
    public ProfileUploadRequest {
        if (file == null) {
            throw new IllegalArgumentException("파일은 필수입니다.");
        }
        if (memberType == null) {
            throw new IllegalArgumentException("유저타입은 필수입니다. ");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId는 필수입니다.");
        }
    }
    public static ProfileImage toEntity(ProfileUploadRequest request, String filePath) {
        return ProfileImage.builder()
                .fileName(request.file.getOriginalFilename())
                .filePath(filePath)
                .build();
    }
}
