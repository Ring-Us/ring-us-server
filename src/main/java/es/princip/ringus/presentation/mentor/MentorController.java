package es.princip.ringus.presentation.mentor;

import es.princip.ringus.application.mentor.service.MentorService;
import es.princip.ringus.domain.support.CursorResponse;
import es.princip.ringus.global.annotation.SessionCheck;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.aop.SessionToMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentor.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Optional;
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
@RequestMapping("/mentor")
public class MentorController implements MentorControllerDocs{

    private final MentorService mentorService;

    @SessionCheck
    @PostMapping
    public ResponseEntity<ApiResponseWrapper<MentorResponse>> create(
            @SessionMemberId Long memberId,
            @Valid @RequestBody MentorRequest request) {
        MentorResponse response = MentorResponse.from(mentorService.register(memberId, request));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }

    @SessionCheck
    @PutMapping
    public ResponseEntity<ApiResponseWrapper<EditMentorResponse>> update(
            @SessionMemberId Long memberId,
            @Valid @RequestBody EditMentorRequest request
    ) {
        EditMentorResponse response = EditMentorResponse.from(mentorService.edit(memberId, request));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponseWrapper<CursorResponse<MentorCardResponse>>> getMentors(
            @ModelAttribute final CursorRequest request,
            @PageableDefault(sort = "mentorId", direction = Sort.Direction.DESC) final Pageable pageable,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {

        Long memberId = Optional.ofNullable(request)
            .filter(CursorRequest::isBookmarked)
            .map(req -> SessionToMemberId.getSessionMemberId(httpServletRequest, httpServletResponse))
            .orElse(null);

        log.info(request.toString());
        log.info(pageable.toString());
        CursorResponse<MentorCardResponse> response = mentorService.getMentorBy(request, pageable, memberId);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }

    @Override
    @GetMapping("/{mentorId}")
    public ResponseEntity<ApiResponseWrapper<MentorDetailResponse>> getMentorByMentorId(
            @PathVariable Long mentorId
    ) {
        MentorDetailResponse response = mentorService.getDetailBy(mentorId);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }
}