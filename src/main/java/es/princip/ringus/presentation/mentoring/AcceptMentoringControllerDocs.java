package es.princip.ringus.presentation.mentoring;

import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentoring.dto.AcceptMentoringRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Mentoring API", description = "멘토링 수락 관련 API")
@RequestMapping("/mentoring/accept")
public interface AcceptMentoringControllerDocs {

    @PatchMapping
    @Operation(summary = "멘토링 수락", description = "멘토링을 수락 합니다.")
    @ApiResponse(responseCode = "200", description = "멘토링 수락 성공")
    @ApiResponse(responseCode = "404", description = "해당 신청이 존재하지 않음")
    @ApiResponse(responseCode = "304", description = "신청 시간 변환 오류")
    @ApiResponse(responseCode = "400", description = "신청 가능 시간 오류")
    @ApiResponse(responseCode = "400", description = "멘토링 대기 상태가 아님")
    @ApiResponse(responseCode = "400", description = "멘티의 제안 시간에 멘토링 시간이 포함되지 않음")
    public ResponseEntity<ApiResponseWrapper<Long>> acceptMentoring(
            @SessionMemberId Long memberId,
            @Valid @RequestBody AcceptMentoringRequest request
    );
}
