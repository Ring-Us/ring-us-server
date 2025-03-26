package es.princip.ringus.presentation.mentee;

import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentee.dto.MyMenteeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "My Mentee API", description = "내 멘티 API")
@RequestMapping("/mentee/me")
public interface MyMenteeControllerDocs {

    @Operation(summary = "내 멘티 상세 조회", description = "내 멘티 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "나의 멘티 상세 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "멘티 프로필을 등록한 적이 없음")
    })
    @GetMapping
    ResponseEntity<ApiResponseWrapper<MyMenteeResponse>> getMyMentee(@SessionMemberId Long memberId);
}
