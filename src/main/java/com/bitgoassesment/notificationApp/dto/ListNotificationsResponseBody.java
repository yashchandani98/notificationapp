package com.bitgoassesment.notificationApp.dto;

import com.bitgoassesment.notificationApp.entity.Notification;
import lombok.Builder;

import java.util.List;

@Builder
public class ListNotificationsResponseBody {
    public String message;

    public List<Notification> notifications;
}
