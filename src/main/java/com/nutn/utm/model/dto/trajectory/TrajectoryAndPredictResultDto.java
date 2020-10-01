package com.nutn.utm.model.dto.trajectory;

import com.nutn.utm.model.dto.ai.TrajectoryFeatureDto;
import com.nutn.utm.model.entity.FlightData;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Getter
@Builder
public class TrajectoryAndPredictResultDto {
    Map<Long, List<FlightData>> executingFlightTrajectory;
    Map<Long, TrajectoryFeatureDto> predictionResult;
}
