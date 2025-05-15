package es.princip.ringus.presentation.notification;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationSender {

    public SseEmitter subscribe(Long memberId);

    public void sendNotification(NotificationEmitterRequest notification);
}
