package es.princip.ringus.presentation.mentor;

import es.princip.ringus.application.mentor.service.MentorService;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentor.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentor")
public class MentorController implements MentorControllerDocs{

    private final MentorService mentorService;

    @PostMapping
    public ResponseEntity<ApiResponseWrapper<MentorResponse>> create(
            @SessionMemberId Long memberId,
            @Valid @RequestBody MentorRequest request) {
        MentorResponse response = MentorResponse.from(mentorService.register(memberId, request));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }

    @PutMapping
    public ResponseEntity<ApiResponseWrapper<EditMentorResponse>> update(
            @SessionMemberId Long memberId,
            @Valid @RequestBody EditMentorRequest request
    ) {
        EditMentorResponse response = EditMentorResponse.from(mentorService.edit(memberId, request));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }

    @Override
    public ResponseEntity<ApiResponseWrapper<MentorCardResponse>> getMyMentor() {
        return null;
    }
}