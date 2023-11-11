package com.smartcode.notificator.service.notification.impl;

import com.smartcode.notificator.mapper.NotificationMapper;
import com.smartcode.notificator.model.dto.notification.NotificationRequestDto;
import com.smartcode.notificator.model.dto.notification.NotificationResponseDto;
import com.smartcode.notificator.model.entity.NotificationEntity;
import com.smartcode.notificator.repository.NotificationRepository;
import com.smartcode.notificator.service.notification.NotificationService;
import com.smartcode.notificator.service.email.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    NotificationMapper notificationMapper;
    NotificationRepository notificationRepository;
    EmailService emailService;

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDto> getNotifications(Boolean sent, Integer userId) {
        List<NotificationEntity> allBySentAndUserId = notificationRepository.findAllBySentAndUserId(sent, userId);
        return notificationMapper.toDtoList(allBySentAndUserId);
    }

    @Override
    @Transactional
    public NotificationResponseDto create(NotificationRequestDto notificationRequestDto) {
        NotificationEntity entity = notificationMapper.toEntity(notificationRequestDto);
        return notificationMapper.toDto(notificationRepository.save(entity));
    }

    @Override
    @Transactional
    public NotificationResponseDto createForVerification(NotificationRequestDto notificationRequestDto) {
        NotificationEntity entity = notificationMapper.toEntity(notificationRequestDto);
        entity.setNotificationDate(System.currentTimeMillis());
        entity.setCreateDate(System.currentTimeMillis());
        emailService.sendSimpleMessage(entity.getEmail(), entity.getTitle(), entity.getContent());
        entity.setSent(true);
        notificationRepository.save(entity);
        return notificationMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationEntity> getReadyNotifications() {
        return notificationRepository.getReadyNotifications(System.currentTimeMillis());
    }

    @Override
    @Transactional
    @Async("notificationSenderExecutors")
    public void sendNotification(NotificationEntity notificationEntity) {
        long waitTime = notificationEntity.getNotificationDate() - System.currentTimeMillis();
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        emailService.sendSimpleMessage(notificationEntity.getEmail(),
                notificationEntity.getTitle(),
                notificationEntity.getContent());
        notificationEntity.setSent(true);
        notificationRepository.save(notificationEntity);
    }
}
