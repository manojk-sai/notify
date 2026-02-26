package com.manoj.notify.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Document(collection = "notifications")
public class Notification {
    @Id
    String id;

    String userId;
    String type;
    Map<String, Object> data;
    NotificationStatus status;

    int retryCount;
    String channel; // email, sms, push

    LocalDateTime createdAt;
    LocalDateTime nextRetryAt;
}
