package com.nutn.utm.model.dto.geojson.trajectory;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Getter
@Builder
@JsonPropertyOrder({
        "planId",
        "macAddress",
        "latestReceivingTime",
        "currentFlyAltitude",
        "currentLocation",
        "predictNextLocation"
})
public class FlightTrajectoryPropertiesDto{

    private String planId;

    private String macAddress;

    private String latestReceivingTime;

    private String currentFlyAltitude;

    private String currentLocation;

    private String predictNextLocation;

}
