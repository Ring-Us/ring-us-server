package es.princip.ringus.presentation.mentoring;

import es.princip.ringus.application.mentor.service.AcceptMentoringService;
import es.princip.ringus.global.annotation.SessionCheck;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentoring.dto.AcceptMentoringRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mentoring/accept")
@RequiredArgsConstructor
public class AcceptMentoringController {
    private final AcceptMentoringService mentoringService;

    @SessionCheck
    @PatchMapping
    public ResponseEntity<ApiResponseWrapper<Long>> acceptMentoring(
            @SessionMemberId Long memberId,
            @Valid @RequestBody AcceptMentoringRequest request
    ) {
         Long id =  mentoringService.accept(request, memberId);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "멘토링 수락 완료", id));
    }
}
