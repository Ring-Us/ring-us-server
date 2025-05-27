package es.princip.ringus.global.sender;

import es.princip.ringus.domain.notification.Notification;

public interface NotificationSender {
    void send(Notification notification);
    NotificationChannel getChannelType();
}