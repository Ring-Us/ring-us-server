package es.princip.ringus.infra.storage.api;

import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.infra.storage.application.StorageService;
import es.princip.ringus.infra.storage.domain.CertificateType;
import es.princip.ringus.infra.storage.dto.CertificateUploadRequest;
import es.princip.ringus.infra.storage.dto.ProfileUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    /**
     * 멘티 증명서 업로드
     */
    @PostMapping("/mentee/certificate")
    public ResponseEntity<ApiResponseWrapper<Void>> uploadMenteeCertificate(
            @ModelAttribute CertificateUploadRequest certificateUploadRequest
            ) {

        String filePath = storageService.uploadMenteeCertificate(certificateUploadRequest);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, filePath));
    }

    /**
     * 멘토 증명서 업로드
     */
    @PostMapping("/mentor/certificate")
    public ResponseEntity<ApiResponseWrapper<Void>> uploadMentorCertificate(
            @ModelAttribute CertificateUploadRequest certificateUploadRequest
    ) {
        String filePath = storageService.uploadMentorCertificate(certificateUploadRequest);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, filePath));
    }

    /**
     * 프로필 이미지 업로드
     */
    @PostMapping("/profile-image")
    public ResponseEntity<ApiResponseWrapper<Void>> uploadProfileImage(
            @ModelAttribute ProfileUploadRequest request
            ) {
        String filePath = storageService.uploadProfileImage(request);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, filePath));
    }
}
