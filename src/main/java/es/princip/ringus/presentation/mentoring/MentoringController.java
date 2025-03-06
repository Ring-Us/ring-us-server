package es.princip.ringus.presentation.mentoring;

import es.princip.ringus.application.mentoring.MentoringService;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentoring.dto.CreateMentoringRequest;
import es.princip.ringus.presentation.mentoring.dto.MentoringResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mentoring")
@RequiredArgsConstructor
public class MentoringController {

    private final MentoringService mentoringService;

    @PostMapping
    public ResponseEntity<ApiResponseWrapper<MentoringResponse>> suggest(
            @SessionMemberId Long memberId,
            @Valid @RequestBody CreateMentoringRequest request) {
        MentoringResponse response = mentoringService.createMentoring(request, memberId);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }


}
