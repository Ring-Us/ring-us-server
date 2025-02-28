package es.princip.ringus.infra.storage.api;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.infra.storage.application.StorageProfileService;
import es.princip.ringus.infra.storage.dto.ProfileUploadRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage/profile")
@RequiredArgsConstructor
public class ProfileImageController {

    private final StorageProfileService storageProfileService;

    /**
     * 프로필 이미지 업로드
     */
    @PostMapping("/profile/image")
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
}
