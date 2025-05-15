package es.princip.ringus.presentation.notification;

import es.princip.ringus.domain.notification.Notification;

public record NotificationEmitterRequest (
        Long senderId,
        Long title,
        String content,
        Long receiverId
) {
    public static NotificationEmitterRequest from(final Notification notification) {
        return new NotificationEmitterRequest(
                notification.getId(),
                notification.getTitle(),
                notification.getContent(),
                notification.getReceiverId()
        );
    }
}
