package es.princip.ringus.global.factory;

import es.princip.ringus.domain.notification.Notification;
import es.princip.ringus.domain.notification.NotificationType;
import es.princip.ringus.global.sender.dto.MentoringRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationMessageFactory {

    public Notification mentoringRequestMessage(MentoringRequestMessage request) {
        String title = request.menteeName() + " 멘티님께서 " + request.mentorName() + " 멘토님께 멘토링을 신청했습니다.";
        String content = "[링어스 멘토링 신청 알림]\n" +
                "멘토링 주제" + request.mentoringTopic().name() + "\n" +
                "신청 시간: " + request.applyTimes().toString() + "\n" +
                "멘토링 신청 메시지: " + request.mentoringMessage() + "\n"+
                "\n\n";
        return Notification.builder()
                .title(title)
                .content(content)
                .type(NotificationType.MENTORING_REQUEST)
                .senderId(request.senderId())
                .receiverId(request.receiverId())
                .build();
    }
}