package com.manoj.notify.channel;

import com.manoj.notify.model.Notification;

public class PushChannel implements NotificationChannel {
    @Override
    public ChannelType getType() {
        return ChannelType.PUSH;
    }

    @Override
    public void sendNotification(Notification notification) throws Exception {
        System.out.println("Sending PUSH to user " + notification.getUserId());
        // Simulate success/failure
        if (Math.random() < 0.2) {
            throw new RuntimeException("Push sending failed");
        }
    }
}
