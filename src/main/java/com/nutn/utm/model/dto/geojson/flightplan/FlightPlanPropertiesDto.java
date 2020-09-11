package com.nutn.utm.model.dto.geojson.flightplan;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({
        "planId",
        "macAddress",
        "maxFlyHeight",
        "startDate",
        "startTime",
        "endTime",
        "description",
        "startPoint",
        "endPoint"
})
public class FlightPlanPropertiesDto {

    private String planId;

    private String macAddress;

    private String maxFlyHeight;

    private String startDate;

    private String startTime;

    private String endTime;

    private String description;

    private String startPoint;

    private String endPoint;

}
