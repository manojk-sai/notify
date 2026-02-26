package com.manoj.notify.repository;

import com.manoj.notify.model.Notification;
import com.manoj.notify.model.NotificationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findTop50ByStatusAndNextRetryAtLessThanEqualOrderByNextRetryAtAsc(
            NotificationStatus status,
            LocalDateTime nextRetryAt
    );
}
