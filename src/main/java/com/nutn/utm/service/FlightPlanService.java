package com.nutn.utm.service;

import com.nutn.utm.model.dto.form.FlightPlanApplicationForm;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.model.entity.Uav;

import java.util.List;
import java.util.Optional;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public interface FlightPlanService {

    FlightPlan applyFlightPlan(long accountId, FlightPlanApplicationForm flightPlanApplicationForm);

    List<FlightPlan> getAllFlightPlansByDate(long accountId, String date);

    FlightPlan getFlightPlanByPlanId(long accountId, String date, long planId);

    FlightPlan getFlightPlanBelongToUavRawData(String macAddress, String date, String time);

    Optional<FlightPlan> getUavFlightPlanBetweenStartTimeAndEndTimeAtTheSameDay(Uav uav, String startTime, String endTime, String date);

    void deleteFlightPlan(long planId);

    FlightPlan modifyFlightPlan(long planId, FlightPlanApplicationForm flightPlanApplicationForm);

}
