package com.nutn.utm.model.dto.response.error;

import lombok.Builder;
import lombok.Getter;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Getter
@Builder
public class FlightPlanWayPointVerifyResultDto {

    private boolean isApprovedApply;

    private String verifyResultMessage;
}
