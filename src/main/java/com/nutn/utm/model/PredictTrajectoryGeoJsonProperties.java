package com.nutn.utm.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Builder
@Getter @Setter
public class PredictTrajectoryGeoJsonProperties {

    private String planId;
    private String macAddress;
    private String latestReceivingTime;
    private String currentFlyAltitude;
    private String currentLocation;
    private String predictNextTrajectoryPoint;

}
