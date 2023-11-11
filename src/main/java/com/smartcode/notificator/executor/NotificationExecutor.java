package com.smartcode.notificator.executor;

import com.smartcode.notificator.model.entity.NotificationEntity;
import com.smartcode.notificator.service.notification.NotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationExecutor {

    NotificationService notificationService;

    @Scheduled(fixedDelay = 30000)
    public void start() {
        List<NotificationEntity> notificationEntities = notificationService.getReadyNotifications();
        for (NotificationEntity notificationEntity : notificationEntities) {
            notificationService.sendNotification(notificationEntity);
        }
    }
}
