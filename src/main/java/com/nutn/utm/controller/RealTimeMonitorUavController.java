package com.nutn.utm.controller;

import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanFeatureCollectionDto;
import com.nutn.utm.model.dto.geojson.trajectory.FlightTrajectoryFeatureCollectionDto;
import com.nutn.utm.model.dto.trajectory.TrajectoryAndPredictResultDto;
import com.nutn.utm.model.dto.trajectory.TrajectoryStabilityDto;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.service.trajectory.RealTimeMonitorUavService;
import com.nutn.utm.utility.geojson.GeoJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@RestController
@RequestMapping("/realTime")
public class RealTimeMonitorUavController {

    @Autowired
    private GeoJsonConverter geoJsonConverter;

    @Autowired
    private RealTimeMonitorUavService realTimeMonitorUavService;

    @GetMapping(value = "/flightPlan/executing")
    FlightPlanFeatureCollectionDto getCurrentlyExecutingFlightPlans(Authentication authentication) {
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        List<FlightPlan> currentlyExecutingFlightPlan = realTimeMonitorUavService.getCurrentlyExecutingFlightPlans(accountId);
        return geoJsonConverter.convertFlightPlansToFeatureCollection(currentlyExecutingFlightPlan);
    }

    @GetMapping(value = "/flightPlan/expired")
    FlightPlanFeatureCollectionDto getExpiredFlightPlansByToday(Authentication authentication) {
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        List<FlightPlan> todayExpiredFlightPlan = realTimeMonitorUavService.getCurrentlyExecutedFlightPlans(accountId);
        return geoJsonConverter.convertFlightPlansToFeatureCollection(todayExpiredFlightPlan);
    }

    @GetMapping(value = "/flightPlan/prepare")
    FlightPlanFeatureCollectionDto getPrepareFlightPlansByToday(Authentication authentication) {
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        List<FlightPlan> todayPrepareFlightPlan = realTimeMonitorUavService.getCurrentlyPrepareFlightPlans(accountId);
        return geoJsonConverter.convertFlightPlansToFeatureCollection(todayPrepareFlightPlan);
    }

    @GetMapping(value = "/trajectory/executing")
    FlightTrajectoryFeatureCollectionDto getExecutingFlightTrajectory(Authentication authentication) {
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        TrajectoryAndPredictResultDto trajectoryAndPredictResultDto = realTimeMonitorUavService.getCurrentlyTrajectoryWithPrediction(accountId);
        return geoJsonConverter.convertTrajectoryWithPrediction(trajectoryAndPredictResultDto.getPredictionResult(), trajectoryAndPredictResultDto.getExecutingFlightTrajectory());
    }

    @GetMapping(value = "/trajectory/executed")
    FlightTrajectoryFeatureCollectionDto getExecutedFlightTrajectory(Authentication authentication) {
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        return geoJsonConverter.convertFlightTrajectoryToFeatureCollection(
                realTimeMonitorUavService.getCurrentlyExecutedFlightTrajectory(accountId));
    }

    @GetMapping(value = "/stability")
    List<TrajectoryStabilityDto> getFlightTrajectoryStability(Authentication authentication) {
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        return realTimeMonitorUavService.getExecutingTrajectoryStability(accountId);
    }

}
