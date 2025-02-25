package es.princip.ringus.presentation.mentee;


import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentee.dto.EditMenteeRequest;
import es.princip.ringus.presentation.mentee.dto.EditMenteeResponse;
import es.princip.ringus.presentation.mentee.dto.MenteeRequest;
import es.princip.ringus.presentation.mentee.dto.MenteeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<ApiResponseWrapper<MenteeResponse>> create(@Valid @RequestBody @Parameter(description = "멘티 등록 요청") MenteeRequest request);

    @Operation(summary = "멘티 수정", description = "기존 멘티 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멘티 수정 성공"),
            @ApiResponse(responseCode = "404", description = "멘티 프로필을 등록한 적이 없음")
    })
    @PutMapping
    ResponseEntity<ApiResponseWrapper<EditMenteeResponse>> update(@Valid @RequestBody @Parameter(description = "멘티 수정 요청") EditMenteeRequest request);
}
