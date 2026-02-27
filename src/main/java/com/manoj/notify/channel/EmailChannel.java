package com.manoj.notify.channel;

import com.manoj.notify.model.Notification;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Component
public class EmailChannel implements NotificationChannel {

    private final SendGrid sendGrid;

    @Value("${sendgrid.from-email}")
    private String fromEmail;

    public EmailChannel(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    @Override
    public ChannelType getType() {
        return ChannelType.EMAIL;
    }

    @Override
    public void sendNotification(Notification notification) {
        try {
            Email from = new Email(fromEmail);
            Email to = new Email("receiver-email@example.com");

            Mail mail = new Mail();
            mail.setFrom(from);
            mail.setSubject("Test Notification");

            Content content = new Content("text/plain",
                    "Hello! This is a real email using SendGrid.");
            mail.addContent(content);

            mail.addPersonalization(
                    new Personalization() {{
                        addTo(to);
                    }}
            );

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            if (response.getStatusCode() >= 400) {
                throw new RuntimeException("Failed: " + response.getBody());
            }

            System.out.println("Email sent successfully!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}