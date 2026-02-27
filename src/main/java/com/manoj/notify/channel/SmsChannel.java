package com.manoj.notify.channel;

import com.manoj.notify.model.Notification;

public class SmsChannel implements NotificationChannel {
    @Override
    public ChannelType getType() {
        return ChannelType.SMS;
    }

    @Override
    public void sendNotification(Notification notification) throws Exception {
        System.out.println("Sending SMS to user " + notification.getUserId());
        // Simulate success/failure
        if (Math.random() < 0.2) {
            throw new RuntimeException("SMS sending failed");
        }
    }
}
