package com.manoj.notify.model;

import lombok.Data;

@Data
public class NotificationPreferences {
    boolean smsEnabled;
    boolean emailEnabled;
    boolean pushEnabled;
    boolean marketingEnabled;
}
