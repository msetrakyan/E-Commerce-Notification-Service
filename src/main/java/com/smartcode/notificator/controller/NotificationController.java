package com.smartcode.notificator.controller;


import com.smartcode.notificator.model.dto.notification.NotificationRequestDto;
import com.smartcode.notificator.model.dto.notification.NotificationResponseDto;
import com.smartcode.notificator.service.notification.NotificationService;
import com.smartcode.notificator.util.constants.Path;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(Path.NOTIFY)
public class NotificationController {

    NotificationService notificationService;

    @PostMapping(Path.CREATE)
    public ResponseEntity<NotificationResponseDto> create(@RequestBody NotificationRequestDto notificationRequestDto) {
        NotificationResponseDto notificationResponseDto = notificationService.create(notificationRequestDto);
        return ResponseEntity.ok(notificationResponseDto);
    }

    @PostMapping(Path.VERIFY)
    public ResponseEntity<NotificationResponseDto> sendVerificationCode(@RequestBody NotificationRequestDto notificationRequestDto) {
        NotificationResponseDto notificationResponseDto = notificationService.createForVerification(notificationRequestDto);
        return ResponseEntity.ok(notificationResponseDto);
    }

    @GetMapping(Path.READY)
    public ResponseEntity<List<NotificationResponseDto>> getReady(@RequestParam Integer userId) {
        List<NotificationResponseDto> readyNotifications = notificationService.getNotifications(true, userId);
        return ResponseEntity.ok(readyNotifications);
    }

    @GetMapping(Path.WAITING)
    public ResponseEntity<List<NotificationResponseDto>> getWaiting(@RequestParam Integer userId) {
        List<NotificationResponseDto> readyNotifications = notificationService.getNotifications(false, userId);
        return ResponseEntity.ok(readyNotifications);
    }

}