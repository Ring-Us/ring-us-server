package es.princip.ringus.presentation.mentor;

import es.princip.ringus.application.mentor.service.MentorService;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.mentor.dto.EditMentorRequest;
import es.princip.ringus.presentation.mentor.dto.EditMentorResponse;
import es.princip.ringus.presentation.mentor.dto.MentorRequest;
import es.princip.ringus.presentation.mentor.dto.MentorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentor")
public class MentorController implements  MentorControllerDocs{

    private final MentorService mentorService;

    @PostMapping
    public ResponseEntity<ApiResponseWrapper<MentorResponse>> create(@Valid @RequestBody MentorRequest request) {
        MentorResponse response = MentorResponse.from(mentorService.register(request));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }

    @PutMapping
    public ResponseEntity<ApiResponseWrapper<EditMentorResponse>> update(@Valid @RequestBody EditMentorRequest request) {
        EditMentorResponse response = EditMentorResponse.from(mentorService.edit(request));
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }
}