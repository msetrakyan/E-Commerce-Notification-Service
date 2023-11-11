package com.smartcode.notificator.service.notification;

import com.smartcode.notificator.model.dto.notification.NotificationRequestDto;
import com.smartcode.notificator.model.dto.notification.NotificationResponseDto;
import com.smartcode.notificator.model.entity.NotificationEntity;

import java.util.List;


public interface NotificationService {
    NotificationResponseDto create(NotificationRequestDto notificationRequestDto);

    List<NotificationResponseDto> getNotifications(Boolean sent, Integer userId);

    NotificationResponseDto createForVerification(NotificationRequestDto notificationRequestDto);

    List<NotificationEntity> getReadyNotifications();

    void sendNotification(NotificationEntity notificationEntity);

}
