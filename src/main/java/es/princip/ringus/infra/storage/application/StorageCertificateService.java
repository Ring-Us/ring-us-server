package es.princip.ringus.infra.storage.application;

import es.princip.ringus.domain.exception.FileErrorCode;
import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.global.util.StoragePathUtil;
import es.princip.ringus.infra.storage.domain.FileMember;
import es.princip.ringus.infra.storage.domain.FileMemberRepository;
import es.princip.ringus.infra.storage.dto.CertificateUploadRequest;
import es.princip.ringus.infra.storage.dto.FilePreviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StorageCertificateService {
    private final S3Service s3Service;
    private final FileMemberRepository fileMemberRepository;
    private final MemberRepository memberRepository;

    /**
     * 멘티 증명서 업로드
     */
    @Transactional
    public String uploadMenteeCertificate(CertificateUploadRequest request, Long memberId) {
        String folderPath = StoragePathUtil.buildCertificateFolderPath(request.certificateType(), false);
        String filePath = s3Service.uploadFile(request.file(), folderPath, false);

        fileMemberRepository.save(request.toFileMemberEntity(filePath, memberId));

        return filePath;
    }

    /**
     * 멘토 증명서 업로드
     */
    @Transactional
    public String uploadMentorCertificate(CertificateUploadRequest request, Long memberId) {
        String folderPath = StoragePathUtil.buildCertificateFolderPath(request.certificateType(), true);
        String filePath = s3Service.uploadFile(request.file(), folderPath, false);

        fileMemberRepository.save(request.toFileMemberEntity(filePath, memberId));

        return filePath;
    }


}
