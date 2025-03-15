package es.princip.ringus.presentation.mentor;

import es.princip.ringus.domain.support.CursorResponse;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentor.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mentor API", description = "멘토 관련 API")
@RequestMapping("/mentor")
public interface MentorControllerDocs {

    @Operation(summary = "멘토 등록", description = "새로운 멘토를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멘토 등록 성공"),
            @ApiResponse(responseCode = "409", description = "이미 가입된 이메일")
    })
    @PostMapping
    ResponseEntity<ApiResponseWrapper<MentorResponse>> create(
            @SessionMemberId Long memberId,
            @Valid @RequestBody @Parameter(description = "멘토 등록 요청") MentorRequest request
    );

    @Operation(summary = "멘토 수정", description = "기존 멘토 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멘토 수정 성공"),
            @ApiResponse(responseCode = "404", description = "멘토 프로필을 등록한 적이 없음")
    })
    @PutMapping
    ResponseEntity<ApiResponseWrapper<EditMentorResponse>> update(
            @SessionMemberId Long memberId,
            @Valid @RequestBody @Parameter(description = "멘토 수정 요청") EditMentorRequest request
    );

    @Operation(summary = "멘토 목록 조회", description = "멘토 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멘토 목록 조회 성공")
    })
    @GetMapping
    ResponseEntity<ApiResponseWrapper<CursorResponse<MentorCardResponse>>> getMentors(
            CursorRequest request,
            Pageable pageable
    );

    @Operation(summary = "멘토 상세 조회", description = "멘토 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멘토 상세 정보 조회 성공")
    })
    @GetMapping("/{mentorId}")
    ResponseEntity<ApiResponseWrapper<MentorDetailResponse>> getMentorByMentorId(
            final Long mentorId
    );
}