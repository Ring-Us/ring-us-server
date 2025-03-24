package es.princip.ringus.infra.storage.api;

import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.infra.storage.dto.FilePreviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "파일 관리 API", description = "파일 관리 API")
@RequestMapping("/storage/files")
public interface FileControllerDocs {

    /**
     * 관리자 전용 - 단일 파일 조회
     */
    @Operation(summary = "단일 파일 조회",
            description = "관리자가 특정 파일의 **Presigned URL**을 조회합니다. 해당 파일은 **S3에서 Presigned URL을 통해 다운로드** 가능합니다.",
            parameters = {
                    @Parameter(name = "fileMemberId", description = "조회할 파일의 고유 ID", required = true)
            })
    @ApiResponse(responseCode = "200", description = "파일 조회 성공")
    @ApiResponse(responseCode = "401", description = "권한 없음 (관리자만 접근 가능)")
    @ApiResponse(responseCode = "404", description = "파일을 찾을 수 없음")
    @GetMapping("/{fileMemberId}")
    ResponseEntity<ApiResponseWrapper<FilePreviewResponse>> getFile(
            @Parameter(description = "파일 멤버 ID", required = true)
            @PathVariable Long fileMemberId
    );

    /**
     * 관리자 전용 - 파일 목록 조회
     */
    @Operation(summary = "파일 목록 조회",
            description = "관리자가 **파일 목록**을 조회할 수 있습니다. 각 파일의 **Presigned URL**이 포함된 목록을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "파일 목록 조회 성공")
    @ApiResponse(responseCode = "401", description = "권한 없음 (관리자만 접근 가능)")
    @ApiResponse(responseCode = "404", description = "파일이 존재하지 않음")
    @GetMapping
    ResponseEntity<ApiResponseWrapper<List<FilePreviewResponse>>> getFiles();
}
