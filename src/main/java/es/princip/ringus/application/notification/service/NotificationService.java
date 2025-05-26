package es.princip.ringus.application.notification.service;

import es.princip.ringus.domain.notification.Notification;
import es.princip.ringus.domain.notification.NotificationRepository;
import es.princip.ringus.global.factory.NotificationMessageFactory;
import es.princip.ringus.global.sender.NotificationSender;
import es.princip.ringus.global.sender.dto.MentoringRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationSender notificationSender;
    private  final NotificationMessageFactory notificationMessageFactory;
    private final NotificationRepository notificationRepository;

    public void notify(MentoringRequestMessage request) {
        Notification notification =  notificationMessageFactory.mentoringRequestMessage(request);
        notificationRepository.save(notification);
        notificationSender.send(notification);
    }
}