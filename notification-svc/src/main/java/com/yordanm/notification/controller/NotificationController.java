package com.yordanm.notification.controller;


import com.yordanm.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.yordanm.apiclientssvc.notification.NotificationRequest;
import com.yordanm.apiclientssvc.notification.NotificationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@AllArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("New notification... {}", notificationRequest);
        NotificationResponse response = notificationService.send(notificationRequest);
        return ResponseEntity.ok(response);
    }
}
