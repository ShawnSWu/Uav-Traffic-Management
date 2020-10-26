package com.nutn.utm.service;

import com.nutn.utm.exception.InvalidRequestException;
import com.nutn.utm.model.dto.form.FlightPlanApplicationForm;
import com.nutn.utm.model.dto.geojson.geography.GeographyLimitAreaFeature;
import com.nutn.utm.model.dto.response.error.FlightPlanWayPointVerifyResultDto;
import com.nutn.utm.model.dto.response.message.ValidationMessage;
import com.nutn.utm.model.entity.Uav;
import com.nutn.utm.utility.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Component
public class FlightPlanFeasibilityValidator {

    @Autowired
    UavService uavService;

    @Autowired
    FlightPlanService flightPlanService;

    @Autowired
    MapGeographyService mapGeographyService;

    private FlightPlanWayPointValidator flightPlanWayPointValidator = new FlightPlanWayPointValidator();

    public void validateFeasibility(FlightPlanApplicationForm planFormDto) {
        String executionDate = planFormDto.getExecutionDate();
        String startTime = planFormDto.getStartTime();
        String endTime = planFormDto.getEndTime();
        Double[][] planCoordinate = planFormDto.getFlightPlanWayPointsDto().getCoordinate();

        if (!isEndTimeLaterThanStartTime(startTime, endTime)) {
            throw new InvalidRequestException(startTime, "endTime",
                    ValidationMessage.EXPECTED_TAKEOFF_TIME_LATER_THAN_ARRIVAL_TIME_MESSAGE);
        }
        if (!isBefore1Hour(executionDate, startTime)) {
            throw new InvalidRequestException(startTime, "startTime",
                    ValidationMessage.EXPECTED_TAKEOFF_TIME_NOT_BEFORE_1Hour_MESSAGE);
        }
        if (isApplicationFormTimeConflictWithOtherFlightPlan(planFormDto.getMacAddress(), planFormDto.getExecutionDate(), startTime, endTime)) {
            throw new InvalidRequestException(startTime, "startTime",
                    String.format(ValidationMessage.PLAN_TIME_CONFLICT, startTime, endTime));
        }

        List<GeographyLimitAreaFeature> forbidAreaFeatures = mapGeographyService.getForbidAreaDto().getFeatures();
        FlightPlanWayPointVerifyResultDto wayPointVerifyResult = flightPlanWayPointValidator.verifyPlanWayPointLegality(planCoordinate, forbidAreaFeatures);
        if (!wayPointVerifyResult.isApprovedApply()) {
            throw new InvalidRequestException(Arrays.deepToString(planCoordinate), "planCoordinate",
                    wayPointVerifyResult.getVerifyResultMessage());
        }

    }

    private boolean isApplicationFormTimeConflictWithOtherFlightPlan(String macAddress, String date, String startTime, String endTime) {
        Uav confirmUav = uavService.getUavIfExists(macAddress);
        return flightPlanService.getUavFlightPlanBetweenStartTimeAndEndTimeAtTheSameDay(confirmUav, endTime, startTime, date).isPresent();
    }


    private boolean isBefore1Hour(String date, String time) {
        LocalDateTime executePlanTime = DateTimeUtils.combineIntoToLocalDateTime(date, time);
        LocalDateTime nowTime = DateTimeUtils.getCurrentLocalDateTime();
        if (executePlanTime.isAfter(nowTime)) {
            Duration timeDifference = Duration.between(nowTime, executePlanTime);
            return timeDifference.toMinutes() >= 60;
        }
        return false;
    }

    private boolean isEndTimeLaterThanStartTime(String startTime, String endTime) {
        LocalTime takeoffTime = DateTimeUtils.convertToLocalTime(startTime);
        LocalTime arrivalsTime = DateTimeUtils.convertToLocalTime(endTime);
        return arrivalsTime.isAfter(takeoffTime);
    }
}
