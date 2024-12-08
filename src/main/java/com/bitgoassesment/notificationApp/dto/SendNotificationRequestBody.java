package com.bitgoassesment.notificationApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SendNotificationRequestBody {
    public String email;

    @JsonProperty("notification_id")
    public String notificationId;
}
