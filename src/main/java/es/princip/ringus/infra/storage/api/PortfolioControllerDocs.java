package es.princip.ringus.infra.storage.api;

import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.infra.storage.dto.PortfolioUploadRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "포트폴리오 업로드 API", description = "멘토 포트폴리오 파일 업로드")
@RequestMapping("/portfolio")
public interface PortfolioControllerDocs {
    @Operation(summary = "멘토 포트폴리오 업로드",
            description = "멘토가 포트폴리오 파일을 업로드합니다. 응답 데이터는 S3에 저장된 파일 경로입니다.",
            requestBody = @RequestBody(
                    description = "업로드할 포트폴리오 파일",
                    required = true,
                    content = @Content(mediaType = "multipart/form-data")
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "업로드 성공, data = filePath",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseWrapper.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "세션 만료 혹은 로그인 필요")
            }
    )
    @PostMapping
    ResponseEntity<ApiResponseWrapper<Void>> uploadPortfolio(@ModelAttribute PortfolioUploadRequest request, HttpSession session);

}
