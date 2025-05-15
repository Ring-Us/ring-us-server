package es.princip.ringus.presentation.notification;

import es.princip.ringus.global.util.ApiResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationEmitterService notificationEmitterService;

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<ApiResponseWrapper<SseEmitter>> subscribe() {
        SseEmitter response = null;
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "연결 성공", response));
    }
}
