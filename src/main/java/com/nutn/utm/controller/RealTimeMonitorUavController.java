package com.nutn.utm.controller;

import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanFeatureCollectionDto;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.service.RealTimeMonitorUavService;
import com.nutn.utm.utility.geojson.GeoJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@CrossOrigin
@RestController
@RequestMapping("/realTime")
public class RealTimeMonitorUavController {

    @Autowired
    GeoJsonConverter geoJsonConverter;

    @Autowired
    RealTimeMonitorUavService realTimeMonitorUavService;

    @GetMapping(value = "/flightPlan/executing")
    FlightPlanFeatureCollectionDto getCurrentlyExecutingFlightPlans(Authentication authentication){
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        List<FlightPlan> currentlyExecutingFlightPlan = realTimeMonitorUavService.getCurrentlyExecutingFlightPlans(accountId);
        return  geoJsonConverter.convertFlightPlansToFeatureCollection(currentlyExecutingFlightPlan);
    }

    @GetMapping(value = "/flightPlan/expired")
    FlightPlanFeatureCollectionDto getExpiredFlightPlansByToday(Authentication authentication){
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        List<FlightPlan> todayExpiredFlightPlan = realTimeMonitorUavService.getCurrentlyExecutedFlightPlans(accountId);
        return geoJsonConverter.convertFlightPlansToFeatureCollection(todayExpiredFlightPlan);
    }

    @GetMapping(value = "/flightPlan/prepare")
    FlightPlanFeatureCollectionDto getPrepareFlightPlansByToday(Authentication authentication){
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        List<FlightPlan> todayPrepareFlightPlan = realTimeMonitorUavService.getCurrentlyPrepareFlightPlans(accountId);
        return geoJsonConverter.convertFlightPlansToFeatureCollection(todayPrepareFlightPlan);
    }

}
