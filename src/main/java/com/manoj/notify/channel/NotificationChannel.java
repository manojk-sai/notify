package com.manoj.notify.channel;

import com.manoj.notify.model.Notification;

public interface NotificationChannel {
    ChannelType getType();
    void sendNotification(Notification notification) throws Exception;
}
