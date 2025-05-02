package es.princip.ringus.infra.storage.application;

import es.princip.ringus.global.util.StoragePathUtil;
import es.princip.ringus.infra.storage.domain.FileMemberRepository;
import es.princip.ringus.infra.storage.dto.ProfileUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StorageProfileImageService {

    private final S3Service s3Service;
    private final FileMemberRepository fileMemberRepository;

    /**
     * 프로필 이미지 업로드
     */
    @Transactional
    public String uploadProfileImage(ProfileUploadRequest request, Long memberId) {
        String folderPath = StoragePathUtil.buildProfileFolderPath(request.memberType());

        String filePath = s3Service.uploadFile(request.file(), folderPath, true);

        fileMemberRepository.save(request.toFileMemberEntity(filePath, memberId));

        return filePath;
    }

}
