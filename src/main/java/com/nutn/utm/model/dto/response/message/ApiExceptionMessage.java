package com.nutn.utm.model.dto.response.message;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface ApiExceptionMessage {

    String NOT_FOUND_FLIGHT_PLAN = "Not found any flight plan.";

    String NOT_FOUND_PILOT = "Not found pilot.";

    String NOT_FOUND_UAV = "This pilot does not own this uav, please confirm again";
}
