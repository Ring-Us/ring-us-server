package es.princip.ringus.presentation.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationEmitterService implements NotificationSender {
    @Value("${app.notification.emitter.timeout}")
    private Long TIMEOUT;

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter subscribe(Long memberId) {
        SseEmitter emitter = new SseEmitter(TIMEOUT);
        emitters.put(memberId, emitter);

        emitter.onCompletion(() -> emitters.remove(memberId));
        emitter.onTimeout(() -> emitters.remove(memberId));

        try {
            emitter.send(SseEmitter.event()
                    .name("connected")
                    .data("SSE 연결 완료"));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @Async
    public void sendNotification(NotificationEmitterRequest notification) {
        SseEmitter emitter = emitters.get(notification.receiverId());
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("notification")
                        .data(notification));
            } catch (IOException e) {
                emitter.completeWithError(e);
                emitters.remove(notification.receiverId());
            }
        }
    }
}
