package es.princip.ringus.infra.storage.api;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.infra.storage.application.StorageProfileImageService;
import es.princip.ringus.infra.storage.dto.ProfileUploadRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storage/profile")
@RequiredArgsConstructor
public class ProfileImageController {

    private final StorageProfileImageService storageProfileService;

    /**
     * 프로필 이미지 업로드
     */
    @PostMapping("/image")
    public ResponseEntity<ApiResponseWrapper<Void>> uploadProfileImage(
            @ModelAttribute ProfileUploadRequest request,
            HttpSession session
    ) {

        Long memberId = (Long)session.getAttribute("memberId");
        if(memberId == null){
            throw new CustomRuntimeException(MemberErrorCode.SESSION_EXPIRED);
        }

        String filePath = storageProfileService.uploadProfileImage(request);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, filePath));
    }


    /**
     * 프로필 이미지 조회
     */
    public ResponseEntity<ApiResponseWrapper<Void>> getProfileImage(
            @RequestParam Long userId,
            HttpSession session
    ) {
        String filePath = storageProfileService.getProfileImage(userId, (Boolean) session.getAttribute("isMentor"));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, filePath));
    }

}
