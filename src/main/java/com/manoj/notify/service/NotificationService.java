package com.manoj.notify.service;

import com.manoj.notify.channel.NotificationChannel;
import com.manoj.notify.dto.NotificationRequest;
import com.manoj.notify.channel.ChannelType;
import com.manoj.notify.model.Notification;
import com.manoj.notify.channel.NotificationChannelFactory;
import com.manoj.notify.model.NotificationStatus;
import com.manoj.notify.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationChannelFactory channelFactory;

    public NotificationService(
            NotificationRepository notificationRepository,
            NotificationChannelFactory channelFactory) {
        this.notificationRepository = notificationRepository;
        this.channelFactory = channelFactory;
    }

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

    public void sendNotification(Notification notification, ChannelType channelType) throws Exception {

        NotificationChannel channel = channelFactory.getChannel(channelType);
        channel.sendNotification(notification);
    }
}
