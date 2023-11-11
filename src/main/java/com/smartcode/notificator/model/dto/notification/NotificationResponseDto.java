package com.smartcode.notificator.model.dto.notification;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationResponseDto {

    Integer id;

    Integer userId;

    String title;

    String content;

    String description;

    Long notificationDate;

    String email;

}
