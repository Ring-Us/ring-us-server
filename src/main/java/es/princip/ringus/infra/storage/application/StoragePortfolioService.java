package es.princip.ringus.infra.storage.application;

import es.princip.ringus.infra.storage.domain.FileMember;
import es.princip.ringus.infra.storage.domain.FileMemberRepository;
import es.princip.ringus.infra.storage.dto.PortfolioUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoragePortfolioService {
    private final S3Service s3Service;
    private final FileMemberRepository fileMemberRepository;

    /**
     * 멘토 포트폴리오 업로드
     */
    @Transactional
    public String uploadMentorPortfolio(PortfolioUploadRequest request, Long memberId) {
        String folderPath = "portfolio/mentor/" + memberId;
        String filePath = s3Service.uploadFile(request.file(), folderPath, false);

        fileMemberRepository.save(request.toFileMemberEntity(filePath, memberId));
        return filePath;
    }
}
