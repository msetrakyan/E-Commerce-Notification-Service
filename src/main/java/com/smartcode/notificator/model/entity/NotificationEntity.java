package com.smartcode.notificator.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    Integer userId;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String content;

    String description;

    @Column(nullable = false)
    Long notificationDate;

    @Column(nullable = false)
    Long createDate;

    @Column(nullable = false)
    Boolean sent;

    @Column(nullable = false)
    String email;

}