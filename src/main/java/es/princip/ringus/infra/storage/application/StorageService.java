package es.princip.ringus.infra.storage.application;


import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.domain.user.UserType;
import es.princip.ringus.infra.storage.domain.Certificate;
import es.princip.ringus.infra.storage.domain.CertificateType;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import es.princip.ringus.infra.storage.dto.CertificateUploadRequest;
import es.princip.ringus.infra.storage.dto.ProfileUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final S3Service s3Service;
    private final MenteeRepository menteeRepository;
    private final MentorRepository mentorRepository;

    public String uploadMenteeCertificate(CertificateUploadRequest request) {

        String folderPath = buildCertificateFolderPath(request.certificateType(), false);
        String uploadedFilePath = s3Service.uploadFile(request.file(), folderPath);

        Certificate certificate = CertificateUploadRequest.toEntity(request, uploadedFilePath);

        Mentee mentee = menteeRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("Mentee not found with id: " + request.userId()));
        mentee.updateCertificate(certificate);
        menteeRepository.save(mentee);

        return uploadedFilePath;
    }

    /**
     * 멘토 증명서 업로드
     */
    public String uploadMentorCertificate(CertificateUploadRequest request) {

        String folderPath = buildCertificateFolderPath(request.certificateType(), true);
        String uploadedFilePath = s3Service.uploadFile(request.file(), folderPath);

        Certificate certificate = CertificateUploadRequest.toEntity(request, uploadedFilePath);

        Mentor mentor = mentorRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("Mentor not found with id: " + request.userId()));
        mentor.updateCertificate(certificate);
        mentorRepository.save(mentor);

        return uploadedFilePath;
    }

    /**
     * 프로필 이미지 업로드
     */
    public String uploadProfileImage(ProfileUploadRequest request) {
        String folderPath = buildProfileFolderPath(request.userType());
        String uploadedFilePath = s3Service.uploadFile(request.file(), folderPath);

        ProfileImage profileImage = ProfileUploadRequest.toEntity(request, uploadedFilePath);

        if(request.userType() == UserType.MENTEE) {
            Mentee mentee = menteeRepository.findById(request.userId())
                    .orElseThrow(() -> new IllegalArgumentException("Mentee not found with id: " + request.userId()));
            mentee.updateProfileImage(profileImage);
            menteeRepository.save(mentee);
        } else if(request.userType() == UserType.MENTOR) {
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
    private String buildProfileFolderPath(UserType userType) {
        return "images/profile/" + userType.name().toLowerCase();
    }
}
