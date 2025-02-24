package es.princip.ringus.infra.storage.dto;

import es.princip.ringus.infra.storage.domain.Certificate;
import es.princip.ringus.infra.storage.domain.CertificateType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record CertificateUploadRequest(
        MultipartFile file,
        CertificateType certificateType,
        Long userId
) {
    // form-data 형식에서는 record 클래스는 자동 바인딩이 안돼서 생성자를 명시적으로 만들어줘야 함
    public CertificateUploadRequest {
        if (file == null) {
            throw new IllegalArgumentException("파일은 필수입니다.");
        }
        if (certificateType == null) {
            throw new IllegalArgumentException("증명서 유형은 필수입니다.");
        }
        if (userId == null) {
            throw new IllegalArgumentException("유저 ID는 필수입니다.");
        }
    }

    public static Certificate toEntity(CertificateUploadRequest request, String filePath) {
        return Certificate.builder()
                .fileName(request.file().getOriginalFilename())
                .filePath(filePath)
                .certificateType(request.certificateType())
                .build();
    }
}
