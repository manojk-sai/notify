package com.manoj.notify.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class user {
    @Id
    String id;
    String email;
    String phone;
    String pushToken;

    NotificationPreferences preferences;
}
