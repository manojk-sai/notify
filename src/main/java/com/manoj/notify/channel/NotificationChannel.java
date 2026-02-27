package com.manoj.notify.channel;

import com.manoj.notify.model.Notification;
import com.manoj.notify.model.User;

public interface NotificationChannel {
    ChannelType getType();
    void sendNotification(Notification notification) throws Exception;
}
