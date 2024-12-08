package com.bitgoassesment.notificationApp.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Notification {
    private String id;
    private double currentPrice;
    private long marketVolume;
    private double intraDayHighPrice;
    private long marketCap;
    private NotificationStatus status;
}
