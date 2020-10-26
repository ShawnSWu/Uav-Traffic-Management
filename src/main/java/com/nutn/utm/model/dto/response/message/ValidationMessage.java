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

    String PLAN_TIME_CONFLICT = "This UAV will be proceeding another flight plan at %s to %s, Please choose another time.";

    String PLAN_WAY_POINT_LEGITIMATE = "Your flight plan route has been approved.";
    String PLAN_WAY_POINT_ILLEGAL = "Your flight plan is illegal, the drone will fly over the「%s」area, please modify your path.";


    String PASSWORD_NOT_CONFORM_FORMAT = "Password must be greater than 8 characters and less than 16 characters.";
    String ACCOUNT_NOT_CONFORM_FORMAT = "Account must be greater than 8 characters and less than 16 characters.";
    String EMAIL_NOT_CONFORM_FORMAT = "Email does not conform the format.";
    String USERNAME_NOT_CONFORM_FORMAT = "The username is missing or the format is incorrect.";
    String PASSWORD_AND_RECONFIRM_DIFFERENCE = "The password does not match the reconfirmed password.";
    String ACCOUNT_AND_PASSWORD_SAME = "Account can't be the same as password.";
}
