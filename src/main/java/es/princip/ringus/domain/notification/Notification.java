package es.princip.ringus.domain.notification;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "notification")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long title;

    // 알림 내용
    @Column(nullable = false)
    private String content;

    // 알림 유형 (ex: 멘토 신청, 수락, 메시지 등)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    // 읽음 여부
    @Column(nullable = false)
    private boolean isRead = false;

    // 수신자 / member 이랑 many to one?
    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Builder
    public Notification(String content, NotificationType type, Long receiverId) {
        this.content = content;
        this.type = type;
        this.receiverId = receiverId;
    }

    public void markAsRead() {
        this.isRead = true;
    }
}

