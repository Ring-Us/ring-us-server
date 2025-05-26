package es.princip.ringus.presentation.notification;

import es.princip.ringus.domain.notification.Notification;
import es.princip.ringus.domain.notification.NotificationType;
import es.princip.ringus.global.annotation.SessionCheck;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.sender.EmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final EmitterRepository emitterRepository;

    @GetMapping("/{receiverId}")
    public void testSend(@PathVariable Long receiverId) {
        Notification n = Notification.builder()
                .title("테스트 알림")
                .content("Postman SSE 테스트입니다.")
                .receiverId(receiverId)
                .type(NotificationType.MENTORING_APPROVED)
                .build();
        try {
            emitterRepository.get(receiverId).ifPresent(emitter -> {
                try {
                    emitter.send(
                        SseEmitter.event()
                            .name("notification")
                            .data(n)  // 직렬화 규칙은 Jackson 기본
                    );
                } catch (IOException e) {
                    emitter.completeWithError(e);
                    emitterRepository.remove(receiverId);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SessionCheck
    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@SessionMemberId Long memberId) {
        SseEmitter emitter = emitterRepository.save(memberId);
        try {
            emitter.send(
                SseEmitter.event()
                    .name("connected")
                    .data("SSE 연결 완료")
            );
        } catch (IOException e) {
            emitter.completeWithError(e);
            emitterRepository.remove(memberId);
        }
        return emitter;
    }
}