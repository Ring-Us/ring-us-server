package es.princip.ringus.global.sender;

import es.princip.ringus.domain.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SseNotificationSender implements NotificationSender {

    private final EmitterRepository emitterRepository;

    @Override
    public NotificationChannel getChannelType() {
        return NotificationChannel.SSE;
    }

    @Override
    public void send(Notification notification) {
        emitterRepository.get(notification.getReceiverId()).ifPresent(emitter -> {
            try {
                emitter.send(
                        SseEmitter.event()
                                .name("notification")
                                .data(notification)   // 직렬화 규칙은 Jackson 기본
                );
            } catch (IOException ex) {
                emitter.completeWithError(ex);
                emitterRepository.remove(notification.getReceiverId());
            }
        });
    }
}