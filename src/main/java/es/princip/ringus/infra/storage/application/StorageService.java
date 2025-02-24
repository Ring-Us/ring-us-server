package es.princip.ringus.infra.storage.application;

import es.princip.ringus.domain.member.MemberType;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.infra.storage.domain.CertificateType;
import es.princip.ringus.infra.storage.domain.ProfileImage;
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
    private final MenteeRepository menteeRepository;
    private final MentorRepository mentorRepository;

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
        String uploadedFilePath = s3Service.uploadFile(request.file(), folderPath);

        ProfileImage profileImage = ProfileUploadRequest.toEntity(request, uploadedFilePath);

        if(request.memberType() == MemberType.MENTEE) {
            Mentee mentee = menteeRepository.findById(request.userId())
                    .orElseThrow(() -> new IllegalArgumentException("Mentee not found with id: " + request.userId()));
            mentee.updateProfileImage(profileImage);
            menteeRepository.save(mentee);
        } else if(request.memberType() == MemberType.MENTOR) {
            Mentor mentor = mentorRepository.findById(request.userId())
                    .orElseThrow(() -> new IllegalArgumentException("Mentor not found with id: " + request.userId()));
            mentor.updateProfileImage(profileImage);
            mentorRepository.save(mentor);
        }
        return uploadedFilePath;
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
