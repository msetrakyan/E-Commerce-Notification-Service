package com.smartcode.notificator.service.consumer;

import com.smartcode.notificator.mapper.NotificationMapper;
import com.smartcode.notificator.model.dto.notification.NotificationRequestDto;
import com.smartcode.notificator.model.entity.NotificationEntity;
import com.smartcode.notificator.repository.NotificationRepository;
import com.smartcode.notificator.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "notificationTopic",
            groupId = "notification",
            containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "true")
    public void receive(@Payload(required = false) NotificationRequestDto notification) {
            notificationService.createForVerification(notification);
    }
}