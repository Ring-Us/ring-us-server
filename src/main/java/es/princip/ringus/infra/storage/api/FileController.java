package es.princip.ringus.infra.storage.api;

import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.infra.storage.application.StorageFileService;
import es.princip.ringus.infra.storage.dto.FilePreviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/storage/files")
@RequiredArgsConstructor
public class FileController implements FileControllerDocs{

    private final StorageFileService storageFileService;
    /**
     * 관리자 전용 - 증명서 presigned URL 발급
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{fileMemberId}")
    public ResponseEntity<ApiResponseWrapper<FilePreviewResponse>> getFile(
            @PathVariable Long fileMemberId
    ) {
        FilePreviewResponse filePreviewResponse = storageFileService.getFile(fileMemberId);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "success",filePreviewResponse));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponseWrapper<List<FilePreviewResponse>>> getFiles(
    ) {
        List<FilePreviewResponse> filePreviewResponses = storageFileService.getFiles();
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "success",filePreviewResponses));
    }
}
