package es.princip.ringus.presentation.mentor;

import es.princip.ringus.application.mentor.service.MyMentorService;
import es.princip.ringus.global.annotation.SessionCheck;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentor.dto.MyMentorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentor/me")
public class MyMentorController implements MyMentorControllerDocs {
    private  final MyMentorService mentorService;

    @SessionCheck
    @Override
    public ResponseEntity<ApiResponseWrapper<MyMentorResponse>> getMyMentor(
            @SessionMemberId Long memberId
    ) {
        MyMentorResponse response = mentorService.getDetailBy(memberId);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }
}
