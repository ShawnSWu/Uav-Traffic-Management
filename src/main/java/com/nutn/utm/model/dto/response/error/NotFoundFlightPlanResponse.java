package com.nutn.utm.model.dto.response.error;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class NotFoundFlightPlanResponse {

    private String message;

    public NotFoundFlightPlanResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
