package com.manoj.notify.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Map;

@Data
public class NotificationRequest {

    @NotBlank
    String userId;

    @NotBlank
    String type;

    Map<String, Object> data;

    String channel; // Optional: "email", "sms", "push"
}
