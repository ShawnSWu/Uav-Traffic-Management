package com.nutn.utm.model.dto.response.message;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface ValidationMessage {



    String MAY_NOT_BE_EMPTY = "Can not be empty.";
    String MAY_NOT_BE_NULL = "Can not be null.";

    String NOT_FOUND_PILOT_MESSAGE = "Not found Pilot.";
    String EXPECTED_TAKEOFF_TIME_NOT_BEFORE_1Hour_MESSAGE = "Expected takeoff time not early 1 hour.";
    String EXPECTED_TAKEOFF_TIME_LATER_THAN_ARRIVAL_TIME_MESSAGE = "Expected takeoff time can not later than arrival time.";
    String FLIGHT_PATH_POINT_LESS_THEN_SPECIFIED_COUNT = "Flight path point not allows less then specified point.";
    String FLIGHT_HEIGHT_BELOW_60_METERS = "Flight height not allows below 60 meters.";

    String PLAN_HAS_BEEN_STARTED_CANNOT_MODIFIED = "The FlightPlan has started and cannot be modified.";
}
