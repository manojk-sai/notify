package com.manoj.notify.controller;

import com.manoj.notify.dto.NotificationRequest;
import com.manoj.notify.model.Notification;
import com.manoj.notify.model.User;
import com.manoj.notify.repository.UserRepository;
import com.manoj.notify.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotificationController {
    private final NotificationService notificationService;
    private final UserRepository userRepository;

    public NotificationController(
            NotificationService notificationService,
            UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    @PostMapping
    @RequestMapping("/notifications")
    @ResponseStatus(HttpStatus.CREATED)
    public Notification create(@Valid @RequestBody NotificationRequest request) {
        return notificationService.createPendingNotification(request);
    }

    @PostMapping
    @RequestMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

}
