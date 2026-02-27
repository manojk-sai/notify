package com.manoj.notify.service;

import com.manoj.notify.channel.ChannelType;
import com.manoj.notify.model.Notification;
import com.manoj.notify.model.NotificationStatus;
import com.manoj.notify.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.time.LocalDateTime;

@Component
public class NotificationWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationWorker.class);
    private static final int MAX_RETRIES = 3;

    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;

    public NotificationWorker(
            NotificationRepository notificationRepository,
            NotificationService notificationService) {
        this.notificationRepository = notificationRepository;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedDelayString = "${notify.worker.poll-interval-ms:5000}")
    public void processPendingNotifications() {
        List<Notification> notifications = notificationRepository
                .findTop50ByStatusAndNextRetryAtLessThanEqualOrderByNextRetryAtAsc(
                        NotificationStatus.PENDING,
                        LocalDateTime.now()
                );

        for (Notification notification : notifications) {
            processNotification(notification);
        }
    }

    private void processNotification(Notification notification) {
        try {
            sendNotification(notification);
            notification.setStatus(NotificationStatus.SENT);
            notificationRepository.save(notification);
        } catch (RuntimeException ex) {
            int nextRetryCount = notification.getRetryCount() + 1;
            notification.setRetryCount(nextRetryCount);

            if (nextRetryCount >= MAX_RETRIES) {
                notification.setStatus(NotificationStatus.FAILED);
            } else {
                notification.setStatus(NotificationStatus.PENDING);
                notification.setNextRetryAt(LocalDateTime.now().plusSeconds(nextRetryCount * 5L));
            }

            LOGGER.error("Failed to process notification {}", notification.getId(), ex);
            notificationRepository.save(notification);
        }
    }

    private void process(Notification notification) {

        try {
            ChannelType channelType = ChannelType.valueOf(notification.getChannel());
            notificationService.sendNotification(notification, channelType);
            notification.setStatus(NotificationStatus.SENT);

        } catch (Exception e) {
            //handleRetry(notification);
        }
    }

    private void sendNotification(Notification notification) {
        LOGGER.info(
                "Processing notification id={} userId={} channel={} type={}",
                notification.getId(),
                notification.getUserId(),
                notification.getChannel(),
                notification.getType()
        );
    }
}
