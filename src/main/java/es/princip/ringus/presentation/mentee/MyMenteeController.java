package es.princip.ringus.presentation.mentee;

import es.princip.ringus.application.mentee.service.MyMenteeService;
import es.princip.ringus.global.annotation.SessionCheck;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentee.dto.MyMenteeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentee/me")
public class MyMenteeController implements MyMenteeControllerDocs {
    private  final MyMenteeService menteeService;

    @SessionCheck
    @Override
    public ResponseEntity<ApiResponseWrapper<MyMenteeResponse>> getMyMentee(
            @SessionMemberId Long memberId
    ) {
        MyMenteeResponse response = menteeService.getDetailBy(memberId);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }
}
