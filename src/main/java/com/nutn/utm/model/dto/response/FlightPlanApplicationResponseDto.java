package com.nutn.utm.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanWayPointsDto;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.utility.DateTimeUtils;


import java.io.IOException;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class FlightPlanApplicationResponseDto {

    private long planId;

    private String macAddress;

    private String executionDate;

    private String startTime;

    private String endTime;

    private int maxFlyingAltitude;

    @JsonProperty("flightPlanWayPoints")
    private FlightPlanWayPointsDto flightPlanWayPoints;

    private String description;

    public FlightPlanApplicationResponseDto(FlightPlan flightPlan) {
        this.planId = flightPlan.getId();
        this.macAddress = flightPlan.getUav().getMacAddress();
        this.executionDate = DateTimeUtils.convertDateToString(flightPlan.getExecutionDate());
        this.startTime = DateTimeUtils.convertTimeToString(flightPlan.getStartTime());
        this.endTime = DateTimeUtils.convertTimeToString(flightPlan.getEndTime());
        this.maxFlyingAltitude = flightPlan.getMaxFlyingAltitude();
        this.flightPlanWayPoints = convertFlightPlanWayPointsToDto(flightPlan.getFlightPlanWayPoints());
        this.description = flightPlan.getDescription();
    }

    private FlightPlanWayPointsDto convertFlightPlanWayPointsToDto(String wayPointsJson) {
        FlightPlanWayPointsDto flightPlanPathDTO = null;

        try {
            flightPlanPathDTO = new ObjectMapper().readValue(wayPointsJson, FlightPlanWayPointsDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flightPlanPathDTO;
    }

    public long getPlanId() {
        return planId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getMaxFlyingAltitude() {
        return maxFlyingAltitude;
    }

    public FlightPlanWayPointsDto getFlightPlanWayPoints() {
        return flightPlanWayPoints;
    }

    public String getDescription() {
        return description;
    }
}
