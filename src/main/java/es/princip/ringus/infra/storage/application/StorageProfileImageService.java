package es.princip.ringus.infra.storage.application;

import es.princip.ringus.global.util.StoragePathUtil;
import es.princip.ringus.infra.storage.dto.ProfileUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StorageProfileImageService {

    private final S3Service s3Service;

    /**
     * 프로필 이미지 업로드
     */
    @Transactional
    public String uploadProfileImage(ProfileUploadRequest request) {
        String folderPath = StoragePathUtil.buildProfileFolderPath(request.memberType());
        return s3Service.uploadFile(request.file(), folderPath);
    }

}
