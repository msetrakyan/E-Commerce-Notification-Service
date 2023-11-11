package com.smartcode.notificator.repository;

import com.smartcode.notificator.model.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
    List<NotificationEntity> findAllBySentAndUserId(Boolean sent, Integer userId);

    @Query(nativeQuery = true,value = "SELECT * from notifications WHERE sent = false AND (notification_date Between :time AND (:time + 30000))")
    List<NotificationEntity> getReadyNotifications(@Param("time") Long time);

}