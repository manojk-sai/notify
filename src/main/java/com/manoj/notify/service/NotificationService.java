package com.manoj.notify.service;

import com.manoj.notify.dto.NotificationRequest;
import com.manoj.notify.model.Notification;
import com.manoj.notify.model.NotificationStatus;
import com.manoj.notify.repository.NotificationRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@NoArgsConstructor
public class NotificationService {
    private NotificationRepository notificationRepository;

    public Notification createPendingNotification(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setType(request.getType());
        notification.setData(request.getData());
        notification.setChannel(request.getChannel());
        notification.setCreatedAt(LocalDateTime.now());
        notification.setStatus(NotificationStatus.PENDING);
        notification.setNextRetryAt(LocalDateTime.now());
        notification.setRetryCount(0);

        return notificationRepository.save(notification);
    }
}
