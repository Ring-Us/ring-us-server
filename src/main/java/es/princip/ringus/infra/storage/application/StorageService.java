package es.princip.ringus.infra.storage.application;

import es.princip.ringus.domain.member.MemberType;
import es.princip.ringus.infra.storage.domain.CertificateType;
import es.princip.ringus.infra.storage.dto.CertificateUploadRequest;
import es.princip.ringus.infra.storage.dto.ProfileUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StorageService {

    private final S3Service s3Service;

    @Transactional
    public String uploadMenteeCertificate(CertificateUploadRequest request) {

        String folderPath = buildCertificateFolderPath(request.certificateType(), false);

        return s3Service.uploadFile(request.file(), folderPath);
    }

    /**
     * 멘토 증명서 업로드
     */
    @Transactional
    public String uploadMentorCertificate(CertificateUploadRequest request) {

        String folderPath = buildCertificateFolderPath(request.certificateType(), true);

        return s3Service.uploadFile(request.file(), folderPath);
    }

    /**
     * 프로필 이미지 업로드
     */
    @Transactional
    public String uploadProfileImage(ProfileUploadRequest request) {
        String folderPath = buildProfileFolderPath(request.memberType());

        return s3Service.uploadFile(request.file(), folderPath);
    }

    // 증명서 폴더 경로 생성 (멘티용)
    private String buildCertificateFolderPath(CertificateType certificateType, Boolean isMentor) {
        if (isMentor) {
            return "certificates/mentor/" + certificateType.name().toLowerCase();
        }
        return "certificates/mentee/" + certificateType.name().toLowerCase();
    }

    // 프로필 이미지 폴더 경로 생성
    private String buildProfileFolderPath(MemberType userType) {
        return "images/profile/" + userType.name().toLowerCase();
    }
}
