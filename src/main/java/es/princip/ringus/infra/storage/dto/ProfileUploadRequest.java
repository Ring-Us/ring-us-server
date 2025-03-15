package es.princip.ringus.infra.storage.dto;

import es.princip.ringus.domain.member.MemberType;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

@Schema(description = "프로필 이미지 업로드 요청 데이터")
public record ProfileUploadRequest(
        @Schema(description = "파일", required = true, example = "profile.jpg")
        MultipartFile file,
        @Schema(description = "유저 타입 ENUM", required = true, example = "MENTOR")
        MemberType memberType
) {
    // 기본생성자 명시적 정의
    public ProfileUploadRequest {
        if (file == null) {
            throw new IllegalArgumentException("파일은 필수입니다.");
        }
        if (memberType == null) {
            throw new IllegalArgumentException("유저타입은 필수입니다. ");
        }
    }
    public static ProfileImage toEntity(ProfileUploadRequest request, String filePath) {
        return ProfileImage.builder()
                .fileName(request.file.getOriginalFilename())
                .filePath(filePath)
                .build();
    }
}
