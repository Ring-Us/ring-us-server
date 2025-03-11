package es.princip.ringus.presentation.mentee;

import es.princip.ringus.application.mentee.service.MenteeService;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentee.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentee")
public class MenteeController implements MenteeControllerDocs{
    private final MenteeService menteeService;

    @PostMapping
    public ResponseEntity<ApiResponseWrapper<MenteeResponse>> create(
            @SessionMemberId Long memberId,
            @Valid @RequestBody MenteeRequest request
    ) {
        MenteeResponse response = MenteeResponse.from(menteeService.register(memberId, request));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }

    @PutMapping
    public ResponseEntity<ApiResponseWrapper<EditMenteeResponse>> update(
            @SessionMemberId Long memberId,
            @Valid @RequestBody EditMenteeRequest request
    ) {
        EditMenteeResponse response = EditMenteeResponse.from(menteeService.edit(memberId, request));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }

    @Override
    public ResponseEntity<ApiResponseWrapper<MyMenteeResponse>> getMyMentee(Long menteeId) {
        return null;
    }
}
