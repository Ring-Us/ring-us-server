package es.princip.ringus.infra.storage.api;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.infra.storage.application.StorageService;
import es.princip.ringus.infra.storage.dto.CertificateUploadRequest;
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
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    /**
     * 멘티 증명서 업로드
     */
    @PostMapping("/certificate/mentee")
    public ResponseEntity<ApiResponseWrapper<Void>> uploadMenteeCertificate(
            @ModelAttribute CertificateUploadRequest certificateUploadRequest,
            HttpSession session
            ) {

        Long memberId = (Long)session.getAttribute("memberId");
        if(memberId == null){
            throw new CustomRuntimeException(MemberErrorCode.SESSION_EXPIRED);
        }

        String filePath = storageService.uploadMenteeCertificate(certificateUploadRequest,memberId);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, filePath));
    }

    /**
     * 멘토 증명서 업로드
     */
    @PostMapping("/certificate/mentor")
    public ResponseEntity<ApiResponseWrapper<Void>> uploadMentorCertificate(
            @ModelAttribute CertificateUploadRequest certificateUploadRequest,
            HttpSession session
    ) {
        Long memberId = (Long)session.getAttribute("memberId");
        if(memberId == null){
            throw new CustomRuntimeException(MemberErrorCode.SESSION_EXPIRED);
        }

        String filePath = storageService.uploadMentorCertificate(certificateUploadRequest, memberId);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, filePath));
    }

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

        String filePath = storageService.uploadProfileImage(request);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, filePath));
    }
}
