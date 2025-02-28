package es.princip.ringus.infra.storage.application;

import es.princip.ringus.global.util.StoragePathUtil;
import es.princip.ringus.infra.storage.domain.FileMemberRepository;
import es.princip.ringus.infra.storage.dto.CertificateUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StorageCertificateService {
    private final S3Service s3Service;
    private final FileMemberRepository fileMemberRepository;

    /**
     * 멘티 증명서 업로드
     */
    @Transactional
    public String uploadMenteeCertificate(CertificateUploadRequest request, Long memberId) {
        String folderPath = StoragePathUtil.buildCertificateFolderPath(request.certificateType(), false);
        String filePath = s3Service.uploadFile(request.file(), folderPath);

        fileMemberRepository.save(request.toFileMemberEntity(filePath, memberId));

        return filePath;
    }

    /**
     * 멘토 증명서 업로드
     */
    @Transactional
    public String uploadMentorCertificate(CertificateUploadRequest request, Long memberId) {
        String folderPath = StoragePathUtil.buildCertificateFolderPath(request.certificateType(), true);
        String filePath = s3Service.uploadFile(request.file(), folderPath);

        fileMemberRepository.save(request.toFileMemberEntity(filePath, memberId));

        return filePath;
    }

}
