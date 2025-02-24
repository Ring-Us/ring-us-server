package es.princip.ringus.presentation.mentee;

import es.princip.ringus.application.mentee.service.MenteeService;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentee.dto.MenteeRequest;
import es.princip.ringus.presentation.mentee.dto.MenteeResponse;
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
@RequestMapping("/mentee")
public class MenteeController {
    private final MenteeService menteeService;

    @PostMapping
    public ResponseEntity<ApiResponseWrapper<MenteeResponse>> create(@Valid @RequestBody MenteeRequest request) {
        MenteeResponse response = MenteeResponse.from(menteeService.register(request));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }
}
