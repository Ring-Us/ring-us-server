package es.princip.ringus.infra.storage.api;

import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.infra.storage.dto.CertificateUploadRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "증명서 업로드 API", description = "멘티 및 멘토 증명서를 업로드하는 API")
@RequestMapping("/storage/certificate")
public interface CertificateControllerDocs {

    @Operation(summary = "멘티 증명서 업로드",
            description = "멘티의 증명서를 업로드합니다. \n\n ⚠️ **multipart/form-data 형식**으로 요청해야 합니다",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(implementation = CertificateUploadRequest.class))

    ))
    @ApiResponse(responseCode = "200", description = "멘티 증명서 업로드 성공",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class)))
    @ApiResponse(responseCode = "401", description = "세션이 만료됨")
    @PostMapping("/mentee")
    ResponseEntity<ApiResponseWrapper<Void>> uploadMenteeCertificate(
            @Parameter(description = "멘티 증명서 업로드 요청 데이터", required = true)
            @ModelAttribute CertificateUploadRequest certificateUploadRequest,

            @Parameter(hidden = true)
            HttpSession session
    );

    @Operation(summary = "멘토 증명서 업로드", description = "멘토의 증명서를 업로드합니다.")
    @ApiResponse(responseCode = "200", description = "멘토 증명서 업로드 성공",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class)))
    @ApiResponse(responseCode = "401", description = "세션이 만료됨")
    @PostMapping("/mentor")
    ResponseEntity<ApiResponseWrapper<Void>> uploadMentorCertificate(
            @Parameter(description = "멘토 증명서 업로드 요청 데이터", required = true)
            @ModelAttribute CertificateUploadRequest certificateUploadRequest,

            @Parameter(hidden = true)
            HttpSession session
    );
}
