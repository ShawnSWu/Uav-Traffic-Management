package com.nutn.utm.controller;

import com.nutn.utm.exception.InvalidRequestException;
import com.nutn.utm.model.dto.form.FlightPlanApplicationForm;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanFeatureCollectionDto;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanFeatureDto;
import com.nutn.utm.model.dto.geojson.trajectory.FlightTrajectoryFeatureCollectionDto;
import com.nutn.utm.model.dto.response.FlightPlanApplicationResponseDto;
import com.nutn.utm.model.entity.FlightData;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.service.flight_plan.FlightPlanService;
import com.nutn.utm.utility.geojson.GeoJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@RestController
@RequestMapping("/flightPlan")
public class FlightPlanController {

    @Autowired
    GeoJsonConverter geoJsonConverter;

    @Autowired
    FlightPlanService flightPlanService;

    @GetMapping("/date/{date}")
    public FlightPlanFeatureCollectionDto getAllFlightPlansByDate(@PathVariable String date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        List<FlightPlan> flightPlans = flightPlanService.getAllFlightPlansByDate(accountId, date);
        return geoJsonConverter.convertFlightPlansToFeatureCollection(flightPlans);
    }

    @GetMapping("/date/{date}/planId/{planId}")
    public FlightPlanFeatureDto getFlightPlanByDateAndId(@PathVariable String date, @PathVariable long planId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        FlightPlan flightPlans = flightPlanService.getFlightPlanByPlanId(accountId, date, planId);
        return geoJsonConverter.convertFlightPlanToFeature(flightPlans);
    }

    @GetMapping("/trajectory/date/{date}")
    public FlightTrajectoryFeatureCollectionDto getAllTrajectoryByDate( @PathVariable String date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        Map<Long, List<FlightData>> flightPlans = flightPlanService.getFlightTrajectoryByDate(accountId, date);
        return geoJsonConverter.convertFlightTrajectoryToFeatureCollection(flightPlans);
    }

    @PostMapping
    public ResponseEntity<FlightPlanApplicationResponseDto> applyFlightPlan(@Valid @RequestBody FlightPlanApplicationForm planApplicationForm, BindingResult formFormatValidateResult) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        if (formFormatValidateResult.hasErrors())
            throw new InvalidRequestException(formFormatValidateResult.getFieldErrors());
        return ResponseEntity.ok(new FlightPlanApplicationResponseDto(flightPlanService.applyFlightPlan(accountId, planApplicationForm)));
    }

    @PutMapping("/id/{planId}")
    public ResponseEntity<FlightPlanApplicationResponseDto> modifyFlightPlan(@RequestBody FlightPlanApplicationForm modifiedPlanForm, @PathVariable long planId) {
        FlightPlan modifiedFlightPlan = flightPlanService.modifyFlightPlan(planId, modifiedPlanForm);
        return ResponseEntity.ok(new FlightPlanApplicationResponseDto(modifiedFlightPlan));
    }

    @DeleteMapping("/id/{planId}")
    public ResponseEntity<Long> deleteFlightPlan(@PathVariable long planId) {
        flightPlanService.deleteFlightPlan(planId);
        return ResponseEntity.ok(planId);
    }

}
