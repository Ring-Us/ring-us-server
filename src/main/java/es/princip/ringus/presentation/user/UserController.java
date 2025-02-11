package es.princip.ringus.presentation.user;

import es.princip.ringus.application.mentee.service.MenteeService;
import es.princip.ringus.application.mentor.service.MentorService;
import es.princip.ringus.global.util.ApiResponse;
import es.princip.ringus.presentation.user.dto.MenteeRequest;
import es.princip.ringus.presentation.user.dto.MentorRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final MentorService mentorService;
    private final MenteeService menteeService;

    @PostMapping("/register/mentor")
    public ResponseEntity<ApiResponse<Void>> registerMentorProfile(@Valid @RequestBody MentorRequest request ){
        mentorService.createMentor(request);

        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "성공"));
    }

    @PostMapping("/register/mentee")
    public ResponseEntity<ApiResponse<Void>> registerMenteeProfile(@Valid @RequestBody MenteeRequest request ){
        menteeService.createMentee(request);

        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "성공"));
    }
}
