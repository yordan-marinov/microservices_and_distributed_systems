package com.yordanm.apiclientssvc.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notification-svc")
public interface NotificationClient{

    @PostMapping("/api/v1/notification")
    ResponseEntity<NotificationResponse> sendNotification(NotificationRequest notificationRequest);
}
