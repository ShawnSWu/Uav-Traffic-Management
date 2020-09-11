package com.nutn.utm.service;

import com.nutn.utm.model.dto.form.FlightPlanApplicationForm;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.model.entity.Uav;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public interface FlightPlanService {

    FlightPlan applyFlightPlan(FlightPlanApplicationForm flightPlanApplicationForm);

    List<FlightPlan> getAllFlightPlansByDate(long accountId, String date);

    FlightPlan getFlightPlanByPlanId(long accountId, String date, long planId);

    FlightPlan getFlightPlanBelongToUavRawData(String macAddress, String date, String time);

    FlightPlan getUavFlightPlanBetweenStartTimeAndEndTimeAtTheSameDay(Uav uav, String startTime, String endTime, String date);

    void deleteFlightPlan(long planId);

    FlightPlan modifyFlightPlan(long planId, FlightPlanApplicationForm flightPlanApplicationForm);

}
