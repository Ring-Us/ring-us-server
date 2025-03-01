package es.princip.ringus.infra.storage.api;

import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.infra.storage.dto.ProfileUploadRequest;
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

@Tag(name = "프로필 이미지 API", description = "프로필 이미지를 업로드하는 API")
@RequestMapping("/storage/profile")
public interface ProfileImageControllerDocs {

    @Operation(
            summary = "프로필 이미지 업로드",
            description = "사용자의 프로필 이미지를 업로드합니다. \n\n ⚠️ **multipart/form-data 형식**으로 요청해야 합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(implementation = ProfileUploadRequest.class))
            )
    )
    @ApiResponse(responseCode = "200", description = "프로필 이미지 업로드 성공",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class)))
    @ApiResponse(responseCode = "401", description = "세션이 만료됨")
    @PostMapping(value = "/image", consumes = "multipart/form-data")
    ResponseEntity<ApiResponseWrapper<Void>> uploadProfileImage(
            @Parameter(description = "프로필 이미지 업로드 요청 데이터 (폼데이터 형식)", required = true)
            @ModelAttribute ProfileUploadRequest request,

            @Parameter(hidden = true)
            HttpSession session
    );
}
