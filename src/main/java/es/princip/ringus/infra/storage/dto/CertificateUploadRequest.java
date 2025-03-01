package es.princip.ringus.infra.storage.dto;

import es.princip.ringus.infra.storage.domain.CertificateType;
import es.princip.ringus.infra.storage.domain.FileMember;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

@Schema(description = "증명서 업로드 요청 데이터")
public record CertificateUploadRequest(
        @Schema(description = "파일", required = true, example = "file.pdf")
        MultipartFile file,
        @Schema(description = "증명서 유형 ENUM", required = true, example = "ENROLLMENT")
        CertificateType certificateType
) {
    // form-data 형식에서는 record 클래스는 자동 바인딩이 안돼서 생성자를 명시적으로 만들어줘야 함
    public CertificateUploadRequest {
        if (file == null) {
            throw new IllegalArgumentException("파일은 필수입니다.");
        }
        if (certificateType == null) {
            throw new IllegalArgumentException("증명서 유형은 필수입니다.");
        }
    }
    public FileMember toFileMemberEntity(String filePath, Long memberId) {
        return FileMember.builder()
                .memberId(memberId)
                .filePath(filePath)
                .build();
    }
}
