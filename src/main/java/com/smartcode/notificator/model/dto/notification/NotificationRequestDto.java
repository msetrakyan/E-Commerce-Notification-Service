package com.smartcode.notificator.model.dto.notification;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
public class NotificationRequestDto implements Serializable {

    private Integer userId;

    private String title;

    private String content;

    private String description;

    private Long notificationDate;

    private Long createDate;

    private Boolean sent;

    private String email;

}
