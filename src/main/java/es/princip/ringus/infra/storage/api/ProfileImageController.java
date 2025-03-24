package es.princip.ringus.infra.storage.api;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.global.annotation.SessionCheck;
import es.princip.ringus.global.annotation.SessionMemberId;
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
public class ProfileImageController implements ProfileImageControllerDocs {

    private final StorageProfileImageService storageProfileService;

    /**
     * 프로필 이미지 업로드
     */
    @PostMapping("/image")
    @SessionCheck
    public ResponseEntity<ApiResponseWrapper<Void>> uploadProfileImage(
            @ModelAttribute ProfileUploadRequest request,
            @SessionMemberId Long memberId
    ) {

        String filePath = storageProfileService.uploadProfileImage(request, memberId);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, filePath));
    }

}
