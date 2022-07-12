package com.yordanm.notification.service;

import com.yordanm.apiclientssvc.notification.NotificationRequest;
import com.yordanm.apiclientssvc.notification.NotificationResponse;
import com.yordanm.notification.model.Notification;
import com.yordanm.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationResponse send(NotificationRequest notificationRequest) {
        saveClient(notificationRequest);
        return new NotificationResponse("Mikey Mouse", notificationRequest.message());
    }

    private void saveClient(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerName())
                        .sender("Mikey Mouse")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}
