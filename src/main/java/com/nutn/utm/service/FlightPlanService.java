package com.nutn.utm.service;

import com.nutn.utm.model.dto.form.FlightPlanApplicationForm;
import com.nutn.utm.model.entity.FlightPlan;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public interface FlightPlanService {

    FlightPlan applyFlightPlan(FlightPlanApplicationForm flightPlanApplicationForm);

    List<FlightPlan> getAllFlightPlansByDate(String pilotAccount, String date);

    FlightPlan getFlightPlanByPlanId(String pilotAccount, String date, long planId);

    void deleteFlightPlan(long planId);

    FlightPlan modifyFlightPlan(long planId, FlightPlanApplicationForm flightPlanApplicationForm);

}
