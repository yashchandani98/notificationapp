package com.bitgoassesment.notificationApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class CreateNotificationResponseBody {
    public String id;
    public String message;
}
