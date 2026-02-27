package com.manoj.notify.channel;

import com.manoj.notify.model.Notification;
import com.manoj.notify.model.User;
import com.manoj.notify.repository.UserRepository;
import com.manoj.notify.utils.EmailTemplateLoader;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Map;

@Component
public class EmailChannel implements NotificationChannel {

    private final SendGrid sendGrid;
    private final UserRepository userRepository;
    private final EmailTemplateLoader templateLoader;

    @Value("${sendgrid.from-email}")
    private String fromEmail;

    public EmailChannel(
            SendGrid sendGrid,
            UserRepository userRepository,
            EmailTemplateLoader templateLoader){
        this.sendGrid = sendGrid;
        this.userRepository = userRepository;
        this.templateLoader = templateLoader;
    }

    @Override
    public ChannelType getType() {
        return ChannelType.EMAIL;
    }

    @Override
    public void sendNotification(Notification notification) {
        String recipientEmail = getRequiredString(notification.getData(), notification.getUserId());
        String subject = getOptionalString(notification.getData(), "subject", "Notification");
        String template = templateLoader.loadTemplate("welcome-email.html");

        try {
            Email from = new Email(fromEmail);
            Email to = new Email(recipientEmail);

            Mail mail = new Mail();
            mail.setFrom(from);
            mail.setSubject(subject);
            mail.addContent(new Content("text/html", template));

            Personalization personalization = new Personalization();
            personalization.addTo(to);
            mail.addPersonalization(personalization);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            if (response.getStatusCode() >= 400) {
                throw new RuntimeException("Failed: " + response.getBody());
            }

            System.out.println("Email sent successfully to " + recipientEmail);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRequiredString(Map<String, Object> data, String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("User not found"));
        if(data == null || user.getEmail() == null) {
            throw new IllegalArgumentException("Missing required field");
        }
        return user.getEmail();
    }

    private String getOptionalString(Map<String, Object> data, String key, String defaultValue) {
        if(data == null || data.get(key)==null) {
            return defaultValue;
        }
        return data.get(key).toString();
    }
}