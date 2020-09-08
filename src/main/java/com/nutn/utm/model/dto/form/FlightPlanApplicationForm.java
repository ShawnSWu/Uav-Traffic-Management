package com.nutn.utm.model.dto.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nutn.utm.model.dto.form.validations.annotations.MinCoordinateCount;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanWayPointsDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class FlightPlanApplicationForm {

    @NotBlank(message = FormValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("pilotAccount")
    private String pilotAccount;

    @NotBlank(message = FormValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("macAddress")
    private String macAddress;

    @NotBlank(message = FormValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("executionDate")
    private String executionDate;

    @NotBlank(message = FormValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("startTime")
    private String startTime;

    @NotBlank(message = FormValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("endTime")
    private String endTime;

    @NotNull(message = FormValidationMessage.MAY_NOT_BE_NULL)
    @Min(value = 10, message = FormValidationMessage.FLIGHT_HEIGHT_BELOW_60_METERS)
    @JsonProperty("maxFlyingAltitude")
    private Integer maxFlyingAltitude;

    @NotNull(message = FormValidationMessage.MAY_NOT_BE_NULL)
    @JsonProperty("flightPlanWayPoints")
    @MinCoordinateCount(count = 2)
    private FlightPlanWayPointsDto flightPlanWayPointsDTO;

    @NotBlank(message = FormValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("description")
    private String description;


    public String getPilotAccount() {
        return pilotAccount;
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

    public Integer getMaxFlyingAltitude() {
        return maxFlyingAltitude;
    }

    public FlightPlanWayPointsDto getFlightPlanWayPointsDto() {
        return flightPlanWayPointsDTO;
    }

    public String getDescription() {
        return description;
    }
}
