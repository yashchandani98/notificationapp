package com.bitgoassesment.notificationApp.repository;

import java.util.*;
import java.util.stream.Collectors;

import com.bitgoassesment.notificationApp.NotificationAppApplication;
import com.bitgoassesment.notificationApp.entity.Notification;
import com.bitgoassesment.notificationApp.entity.NotificationStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public class NotificationRepository {
    Map<String, Notification> notifications = new HashMap<>();
    public Notification createNotification(double currentPrice, long marketVolume, double intradayHighPrice, long marketCap) {
        String id = UUID.randomUUID().toString();
        Notification notification = Notification.builder()
                .id(id)
                .currentPrice(currentPrice)
                .marketVolume(marketVolume)
                .intraDayHighPrice(intradayHighPrice)
                .marketCap(marketCap)
                .status(NotificationStatus.OUTSTANDING)
                .build();
        notifications.put(id, notification);
        return notification;
    }

    public List<Notification> listNotifications(NotificationStatus status){
        return notifications.values().stream().filter(n -> n.getStatus().equals(status)).collect(Collectors.toList());
    }

    public Notification getNotificationById(String id){
        Optional<Notification> notification = notifications.values()
                .stream()
                .filter( not -> not.getStatus()!=NotificationStatus.DELETED && not.getId().equals(id))
                .findAny();
        return notification.get();
    }

    public void updateNotificationStatus(String id, NotificationStatus status){
        Notification notification = getNotificationById(id);
        notification.setStatus(status);
    }

}
