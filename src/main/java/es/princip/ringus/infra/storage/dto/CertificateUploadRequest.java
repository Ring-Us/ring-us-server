package es.princip.ringus.infra.storage.dto;

import es.princip.ringus.infra.storage.domain.Certificate;
import es.princip.ringus.infra.storage.domain.CertificateType;
import org.springframework.web.multipart.MultipartFile;

public record CertificateUploadRequest(
        MultipartFile file,
        CertificateType certificateType,
        Long userId
) {
    public static Certificate toEntity(CertificateUploadRequest request, String filePath) {
        return Certificate.builder()
                .fileName(request.file().getOriginalFilename())
                .filePath(filePath)
                .certificateType(request.certificateType())
                .build();
    }
}
