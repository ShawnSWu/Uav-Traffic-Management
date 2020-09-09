package com.nutn.utm.service;

import com.nutn.utm.exception.InvalidRequestException;
import com.nutn.utm.model.dto.form.FlightPlanApplicationForm;
import com.nutn.utm.model.dto.response.message.ValidationMessage;
import com.nutn.utm.utility.DateTimeUtils;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Component
public class FlightPlanFeasibilityValidator {

    public void validateFeasibility(FlightPlanApplicationForm planFormDTO) {
        if (!isBefore1Hour(planFormDTO)) {
            throw new InvalidRequestException(planFormDTO.getStartTime(), "expectedStartTime",
                    ValidationMessage.EXPECTED_TAKEOFF_TIME_NOT_BEFORE_1Hour_MESSAGE);
        }
        if (!isArrivalTimeLaterThanTakeoffTime(planFormDTO)) {
            throw new InvalidRequestException(planFormDTO.getStartTime(), "expectedEndTime",
                    ValidationMessage.EXPECTED_TAKEOFF_TIME_LATER_THAN_ARRIVAL_TIME_MESSAGE);
        }
    }


    private boolean isBefore1Hour(FlightPlanApplicationForm planFormDTO) {
        String date = planFormDTO.getExecutionDate();
        String time = planFormDTO.getStartTime();
        LocalDateTime executeTaskTime = DateTimeUtils.combineIntoToLocalDateTime(date, time);
        LocalDateTime nowTime = DateTimeUtils.getCurrentLocalDateTime();
        if (executeTaskTime.isAfter(nowTime)) {
            Duration timeDifference = Duration.between(nowTime, executeTaskTime);
            return timeDifference.toMinutes() >= 60;
        }
        return false;
    }

    private boolean isArrivalTimeLaterThanTakeoffTime(FlightPlanApplicationForm planFormDTO) {
        String endTime = planFormDTO.getEndTime();
        String startTime = planFormDTO.getStartTime();
        LocalTime takeoffTime = DateTimeUtils.convertToLocalTime(startTime);
        LocalTime arrivalsTime = DateTimeUtils.convertToLocalTime(endTime);
        return arrivalsTime.isAfter(takeoffTime);
    }
}
