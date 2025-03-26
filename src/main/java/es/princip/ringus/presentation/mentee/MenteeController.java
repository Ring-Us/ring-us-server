package es.princip.ringus.presentation.mentee;

import es.princip.ringus.application.mentee.service.MenteeService;
import es.princip.ringus.domain.support.CursorResponse;
import es.princip.ringus.global.annotation.SessionCheck;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentee.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mentee")
public class MenteeController implements MenteeControllerDocs{
    private final MenteeService menteeService;

    @SessionCheck
    @PostMapping
    public ResponseEntity<ApiResponseWrapper<MenteeResponse>> create(
            @SessionMemberId Long memberId,
            @Valid @RequestBody MenteeRequest request
    ) {
        MenteeResponse response = MenteeResponse.from(menteeService.register(memberId, request));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }

    @SessionCheck
    @PutMapping
    public ResponseEntity<ApiResponseWrapper<EditMenteeResponse>> update(
            @SessionMemberId Long memberId,
            @Valid @RequestBody EditMenteeRequest request
    ) {
        EditMenteeResponse response = EditMenteeResponse.from(menteeService.edit(memberId, request));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }

    @Override
    @SessionCheck
    @GetMapping
    public ResponseEntity<ApiResponseWrapper<CursorResponse<MenteeCardResponse>>> getMyMentee(
        @ModelAttribute final MenteeCursorRequest request,
        @PageableDefault(sort = "menteeId", direction = Sort.Direction.DESC) final Pageable pageable,
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        @SessionMemberId Long memberId
    ) {

        log.info(request.toString());
        log.info(pageable.toString());

        CursorResponse<MenteeCardResponse> response = menteeService.getMenteeBy(request, pageable, memberId);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }
}
