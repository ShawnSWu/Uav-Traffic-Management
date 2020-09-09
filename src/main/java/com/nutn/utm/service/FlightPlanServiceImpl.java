package com.nutn.utm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutn.utm.exception.InvalidRequestException;
import com.nutn.utm.exception.NotFoundFlightPlanException;
import com.nutn.utm.model.dto.form.FlightPlanApplicationForm;
import com.nutn.utm.model.dto.response.message.ValidationMessage;
import com.nutn.utm.model.dto.response.message.ApiExceptionMessage;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.model.entity.Pilot;
import com.nutn.utm.model.entity.Uav;
import com.nutn.utm.repository.FlightPlanRepository;
import com.nutn.utm.repository.PilotRepository;
import com.nutn.utm.repository.UavRepository;
import com.nutn.utm.utility.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Service
@Transactional
public class FlightPlanServiceImpl implements FlightPlanService {

    @Autowired
    FlightPlanRepository flightPlanRepository;

    @Autowired
    UavRepository uavRepository;

    @Autowired
    PilotRepository pilotRepository;

    @Autowired
    FlightPlanFeasibilityValidator flightPlanFeasibilityValidator;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public FlightPlan applyFlightPlan(FlightPlanApplicationForm flightPlanApplicationForm) {
        flightPlanFeasibilityValidator.validateFeasibility(flightPlanApplicationForm);

        Uav confirmedUav = confirmUavIsExists(flightPlanApplicationForm.getMacAddress());
        Date executionDate = DateTimeUtils.convertToDate(flightPlanApplicationForm.getExecutionDate());
        Date startTime = DateTimeUtils.convertToTime(flightPlanApplicationForm.getStartTime());
        Date endTime = DateTimeUtils.convertToTime(flightPlanApplicationForm.getEndTime());
        int expectedFlyingAltitude = flightPlanApplicationForm.getMaxFlyingAltitude();
        String description = flightPlanApplicationForm.getDescription();
        String planWayPoints = "";
        try {
            planWayPoints = objectMapper.writeValueAsString(flightPlanApplicationForm.getFlightPlanWayPointsDto());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        FlightPlan flightPlan = new FlightPlan(confirmedUav, executionDate, startTime,
                endTime, expectedFlyingAltitude, planWayPoints, description);
        return flightPlanRepository.save(flightPlan);
    }

    @Override
    public List<FlightPlan> getAllFlightPlansByDate(String pilotAccount, String date) {
        Pilot confirmedPilot = confirmPilotIsExists(pilotAccount);
        return flightPlanRepository.findAllByUavPilotAndExecutionDateEquals(confirmedPilot, DateTimeUtils.convertToDate(date));
    }

    @Override
    public FlightPlan getFlightPlanByPlanId(String pilotAccount, String date, long planId) {
        Pilot confirmedPilot = confirmPilotIsExists(pilotAccount);
        Optional<FlightPlan> flightPlan = flightPlanRepository.findByUavPilotAndExecutionDateAndId(confirmedPilot, DateTimeUtils.convertToDate(date), planId);
        if (!flightPlan.isPresent()) {
            throw new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_FLIGHT_PLAN);
        }
        return flightPlan.get();
    }

    private Pilot confirmPilotIsExists(String pilotAccount){
        Pilot pilot = pilotRepository.findByAccount(pilotAccount);
        if (!Optional.ofNullable(pilot).isPresent())
            throw new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_PILOT);
        return pilot;
    }

    private Uav confirmUavIsExists(String macAddress){
        Uav uav = uavRepository.findByMacAddress(macAddress);
        if (!Optional.ofNullable(uav).isPresent())
            throw new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_UAV);
        return uav;
    }

    @Override
    public void deleteFlightPlan(long planId) {
        flightPlanRepository.deleteById(planId);
    }

    @Override
    public FlightPlan modifyFlightPlan(long planId, FlightPlanApplicationForm modifiedFlightPlanForm) {
        Optional<FlightPlan> flightPlan = flightPlanRepository.findById(planId);
        FlightPlan modifiedFlightPlan = new FlightPlan();
        flightPlan.ifPresent(originalFlightPlan -> {
            if (isFlightPlanNotStartYet(originalFlightPlan)) {
                modifiedFlightPlan.setId(originalFlightPlan.getId());

                if (isUavHasBeenModified(modifiedFlightPlanForm, originalFlightPlan)) {
                    Uav uav = uavRepository.findByMacAddress(modifiedFlightPlanForm.getMacAddress());
                    modifiedFlightPlan.setUav(uav);
                } else {
                    modifiedFlightPlan.setUav(originalFlightPlan.getUav());
                }

                String modifiedWayPoints = "";
                try {
                    modifiedWayPoints = objectMapper.writeValueAsString(modifiedFlightPlanForm.getFlightPlanWayPointsDto());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                modifiedFlightPlan.setFlightPlanWayPoints(modifiedWayPoints);
                modifiedFlightPlan.setMaxFlyingAltitude(modifiedFlightPlanForm.getMaxFlyingAltitude());
                modifiedFlightPlan.setExecutionDate(DateTimeUtils.convertToDate(modifiedFlightPlanForm.getExecutionDate()));
                modifiedFlightPlan.setStartTime(DateTimeUtils.convertToTime(modifiedFlightPlanForm.getStartTime()));
                modifiedFlightPlan.setEndTime(DateTimeUtils.convertToTime(modifiedFlightPlanForm.getEndTime()));
                modifiedFlightPlan.setDescription(modifiedFlightPlanForm.getDescription());
                flightPlanRepository.save(modifiedFlightPlan);
            } else {
                throw new InvalidRequestException(String.valueOf(originalFlightPlan.getId()), "id",
                        ValidationMessage.PLAN_HAS_BEEN_STARTED_CANNOT_MODIFIED);
            }
        });
        return modifiedFlightPlan;
    }

    private boolean isUavHasBeenModified(FlightPlanApplicationForm modifiedFlightPlanForm, FlightPlan originFlightPlan) {
        return !modifiedFlightPlanForm.getMacAddress().equals(originFlightPlan.getUav().getMacAddress());
    }

    private boolean isFlightPlanNotStartYet(FlightPlan flightPlan) {
        String date = DateTimeUtils.convertDateToString(flightPlan.getExecutionDate());
        String time = DateTimeUtils.convertTimeToString(flightPlan.getExecutionDate());
        LocalDateTime startDateTime = DateTimeUtils.combineIntoToLocalDateTime(date, time);
        LocalDateTime now = DateTimeUtils.getCurrentLocalDateTime();
        return startDateTime.isBefore(now);
    }
}
