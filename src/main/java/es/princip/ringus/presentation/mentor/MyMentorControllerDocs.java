package es.princip.ringus.presentation.mentor;

import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentor.dto.MyMentorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "My Mentor API", description = "내 멘토 API")
@RequestMapping("/mentor/me")
public interface MyMentorControllerDocs {

    @Operation(summary = "내 멘토 조회(구현 중)", description = "기존 멘토 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "나의 멘토 조회 성공"),
            @ApiResponse(responseCode = "404", description = "멘토 프로필을 등록한 적이 없음")
    })
    @GetMapping
    ResponseEntity<ApiResponseWrapper<MyMentorResponse>> getMentor(@SessionMemberId Long memberId);
}
