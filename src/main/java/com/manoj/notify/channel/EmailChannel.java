package com.manoj.notify.channel;

import com.manoj.notify.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class EmailChannel implements NotificationChannel {
    @Override
    public ChannelType getType() {
        return ChannelType.EMAIL;
    }

    @Override
    public void sendNotification(Notification notification) throws Exception {
        System.out.println("Sending EMAIL to user " + notification.getUserId());
        // Simulate success/failure
        if (Math.random() < 0.2) {
            throw new RuntimeException("Email sending failed");
        }
    }
}
