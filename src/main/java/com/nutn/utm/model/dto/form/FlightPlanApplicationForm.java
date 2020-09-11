package com.nutn.utm.model.dto.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nutn.utm.model.dto.form.validations.annotations.MinCoordinateCount;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanWayPointsDto;
import com.nutn.utm.model.dto.response.message.ValidationMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightPlanApplicationForm {

    @NotBlank(message = ValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("accountId")
    private long accountId;

    @NotBlank(message = ValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("macAddress")
    private String macAddress;

    @NotBlank(message = ValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("executionDate")
    private String executionDate;

    @NotBlank(message = ValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("startTime")
    private String startTime;

    @NotBlank(message = ValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("endTime")
    private String endTime;

    @NotNull(message = ValidationMessage.MAY_NOT_BE_NULL)
    @Min(value = 10, message = ValidationMessage.FLIGHT_HEIGHT_BELOW_60_METERS)
    @JsonProperty("maxFlyingAltitude")
    private Integer maxFlyingAltitude;

    @NotNull(message = ValidationMessage.MAY_NOT_BE_NULL)
    @JsonProperty("flightPlanWayPoints")
    @MinCoordinateCount(count = 2)
    private FlightPlanWayPointsDto flightPlanWayPointsDto;

    @NotBlank(message = ValidationMessage.MAY_NOT_BE_EMPTY)
    @JsonProperty("description")
    private String description;

}
