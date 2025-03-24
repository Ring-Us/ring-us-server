package es.princip.ringus.presentation.mentoring;

import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentoring.dto.CreateMentoringRequest;
import es.princip.ringus.presentation.mentoring.dto.MentoringResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Mentoring API", description = "멘토링 관련 API")
@RequestMapping("/mentoring")
public interface MentoringControllerDocs {

    @Operation(summary = "멘토링 제안", description = "멘토링을 제안합니다.")
    @ApiResponse(responseCode = "200", description = "멘토링 제안 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자")
    @ApiResponse(responseCode = "404", description = "멘토 또는 멘티가 존재하지 않음")
    @PostMapping
    ResponseEntity<ApiResponseWrapper<MentoringResponse>> suggest(
            @SessionMemberId Long memberId,
            @Valid @RequestBody @Parameter CreateMentoringRequest request
    );
}
