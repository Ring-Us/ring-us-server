package es.princip.ringus.infra.storage.application;

import es.princip.ringus.domain.exception.MenteeErrorCode;
import es.princip.ringus.domain.exception.MentorErrorCode;
import es.princip.ringus.domain.exception.ProfileErrorCode;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.global.util.StoragePathUtil;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import es.princip.ringus.infra.storage.dto.ProfileUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StorageProfileImageService {

    private final S3Service s3Service;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

    /**
     * 프로필 이미지 업로드
     */
    @Transactional
    public String uploadProfileImage(ProfileUploadRequest request) {
        String folderPath = StoragePathUtil.buildProfileFolderPath(request.memberType());
        return s3Service.uploadFile(request.file(), folderPath);
    }

    /**
     * 특정 사용자의 프로필 이미지 조회
     */
    public String getProfileImage(Long userId, boolean isMentor) {
        if (isMentor) {
            // 멘토 프로필 이미지 조회
            Mentor mentor = mentorRepository.findById(userId)
                    .orElseThrow(() -> new CustomRuntimeException(MentorErrorCode.MENTOR_NOT_FOUND));
            return getProfileImagePath(mentor.getProfileImage());
        } else {
            // 멘티 프로필 이미지 조회
            Mentee mentee = menteeRepository.findById(userId)
                    .orElseThrow(() -> new CustomRuntimeException(MenteeErrorCode.MENTEE_NOT_FOUND));
            return getProfileImagePath(mentee.getProfileImage());
        }
    }

    /**
     * 프로필 이미지 파일 경로 반환
     */
    private String getProfileImagePath(ProfileImage profileImage) {
        if (profileImage == null || profileImage.getFilePath() == null) {
            throw new CustomRuntimeException(ProfileErrorCode.PROFILE_NOT_FOUND);
        }
        return profileImage.getFilePath();
    }
}
