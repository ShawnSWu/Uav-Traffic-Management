package com.nutn.utm.model.dto.trajectory;

import lombok.Builder;
import lombok.Getter;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Getter
@Builder
public class TrajectoryStabilityDto {

    private long planId;

    private double stability;
}
