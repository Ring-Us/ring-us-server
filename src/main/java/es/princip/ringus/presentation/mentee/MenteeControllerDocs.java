package es.princip.ringus.presentation.mentee;


import es.princip.ringus.domain.support.CursorResponse;
import es.princip.ringus.global.annotation.SessionCheck;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentee.dto.EditMenteeRequest;
import es.princip.ringus.presentation.mentee.dto.EditMenteeResponse;
import es.princip.ringus.presentation.mentee.dto.MenteeCardResponse;
import es.princip.ringus.presentation.mentee.dto.MenteeCursorRequest;
import es.princip.ringus.presentation.mentee.dto.MenteeRequest;
import es.princip.ringus.presentation.mentee.dto.MenteeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Mentee API", description = "멘티 관련 API")
@RequestMapping("/mentee")
public interface MenteeControllerDocs {

    @Operation(summary = "멘티 등록", description = "새로운 멘티를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멘티 등록 성공"),
            @ApiResponse(responseCode = "409", description = "이미 가입된 이메일")
    })
    @PostMapping
    ResponseEntity<ApiResponseWrapper<MenteeResponse>> create(
            @SessionMemberId Long memberId,
            @Valid @RequestBody @Parameter(description = "멘티 등록 요청") MenteeRequest request
    );

    @Operation(summary = "멘티 수정", description = "기존 멘티 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멘티 수정 성공"),
            @ApiResponse(responseCode = "404", description = "멘티 프로필을 등록한 적이 없음")
    })
    @PutMapping
    ResponseEntity<ApiResponseWrapper<EditMenteeResponse>> update(
            @SessionMemberId Long memberId,
            @Valid @RequestBody @Parameter(description = "멘티 수정 요청") EditMenteeRequest request
    );

    @Operation(summary = "멘티 상세 조회(구현 중)", description = "멘티 상세 조회를 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멘티 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 id의 멘티가 존재하지 않음")
    })

    @SessionCheck
    @GetMapping
    ResponseEntity<ApiResponseWrapper<CursorResponse<MenteeCardResponse>>>  getMyMentee(
        @ModelAttribute MenteeCursorRequest request,
        @PageableDefault(sort = "menteeId", direction = Sort.Direction.DESC) Pageable pageable,
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        @SessionMemberId Long memberId
    );
}
