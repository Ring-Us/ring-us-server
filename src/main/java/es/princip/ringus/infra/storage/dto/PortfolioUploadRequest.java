package es.princip.ringus.infra.storage.dto;

import es.princip.ringus.infra.storage.domain.FileMember;
import org.springframework.web.multipart.MultipartFile;

public record PortfolioUploadRequest(
        MultipartFile file
) {

    public FileMember toFileMemberEntity(String filePath, Long memberId) {
        return FileMember.builder()
                .memberId(memberId)
                .filePath(filePath)
                .build();
    }
}
