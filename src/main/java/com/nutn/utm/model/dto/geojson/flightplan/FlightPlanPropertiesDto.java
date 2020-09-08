package com.nutn.utm.model.dto.geojson.flightplan;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setMaxFlyHeight(String maxFlyHeight) {
        this.maxFlyHeight = maxFlyHeight;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getPlanId() {
        return planId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getMaxFlyHeight() {
        return maxFlyHeight;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
