package com.bitgoassesment.notificationApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateNotificationRequestBody {

    @JsonProperty("current_price")
    private double currentPrice;

    @JsonProperty("market_trade_volume")
    private long marketVolume;

    @JsonProperty("intraday_high_price")
    private double intradayHighPrice;

    @JsonProperty("market_cap")
    private long marketCap;


}
