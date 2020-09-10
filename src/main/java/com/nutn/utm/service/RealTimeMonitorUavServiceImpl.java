package com.nutn.utm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutn.utm.model.UavRawData;
import com.nutn.utm.model.dto.mqtt.LoRaGatewayMessage;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.model.entity.TrajectoryPoint;
import com.nutn.utm.repository.TrajectoryPointRepository;
import com.nutn.utm.utility.DateTimeUtils;
import com.nutn.utm.utility.cayenne.CayenneLPPDataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.WeakHashMap;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Service
public class RealTimeMonitorUavServiceImpl implements RealTimeMonitorUavService {

    private WeakHashMap<String, FlightPlan> proceedingFlightPlanCache = new WeakHashMap<>();

    @Autowired
    private FlightPlanService flightPlanService;

    @Autowired
    TrajectoryPointRepository trajectoryPointRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void receiveMqttBrokerMessage(String message) {
        StringBuilder gatewayPacketBuilder = new StringBuilder(message);
        gatewayPacketBuilder.deleteCharAt(0);
        gatewayPacketBuilder.deleteCharAt(gatewayPacketBuilder.length() - 1);
        String gatewayPacketString = gatewayPacketBuilder.toString();
        try {
            LoRaGatewayMessage loRaGatewayMessage = objectMapper.readValue(gatewayPacketString, LoRaGatewayMessage.class);
            saveRealTimeUavRawData(loRaGatewayMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveRealTimeUavRawData(LoRaGatewayMessage loRaGatewayMessage) {
        UavRawData uavRawData = CayenneLPPDataParser.parser(loRaGatewayMessage.getData());
        String macAddress = loRaGatewayMessage.getMacAddr();
        String date = LocalDate.now().toString();
        String time = LocalTime.now().withNano(0).toString();

        Optional<FlightPlan> belongToUavRawDataPlan = findFlightPlanBelongToUavRawData(macAddress, date, time);
        belongToUavRawDataPlan.ifPresent(plan -> {
            TrajectoryPoint trajectoryPoint = TrajectoryPoint.builder()
                    .date(DateTimeUtils.convertToDate(date))
                    .time(DateTimeUtils.convertToTime(time))
                    .latitude(uavRawData.getGps().getLatitude())
                    .longitude(uavRawData.getGps().getLongitude())
                    .altitude(uavRawData.getGps().getAltitude())
                    .yaw(uavRawData.getAttitude().getYaw())
                    .roll(uavRawData.getAttitude().getRoll())
                    .pitch(uavRawData.getAttitude().getPitch())
                    .gyroX(uavRawData.getGyrometer().getX())
                    .gyroY(uavRawData.getGyrometer().getY())
                    .gyroZ(uavRawData.getGyrometer().getZ())
                    .accX(uavRawData.getAccelerometer().getX())
                    .accY(uavRawData.getAccelerometer().getY())
                    .accZ(uavRawData.getAccelerometer().getZ())
                    .nedNorth(uavRawData.getNedCoordinate().getNorth())
                    .nedDown(uavRawData.getNedCoordinate().getEast())
                    .nedEast(uavRawData.getNedCoordinate().getEast())
                    .flightPlan(plan)
                    .hex_data_packet(loRaGatewayMessage.getData()).build();
            trajectoryPointRepository.save(trajectoryPoint);
        });
    }

    private Optional<FlightPlan> findFlightPlanBelongToUavRawData(String macAddress, String date, String time) {
        FlightPlan belongToUavRawDataFlightPlan;
        if (!proceedingFlightPlanCache.containsKey(macAddress)) {
            belongToUavRawDataFlightPlan = flightPlanService.findFlightPlanBelongToUavRawData(macAddress, date, time);
            if (Optional.ofNullable(belongToUavRawDataFlightPlan).isPresent())
                proceedingFlightPlanCache.put(macAddress, belongToUavRawDataFlightPlan);
        } else {
            belongToUavRawDataFlightPlan = proceedingFlightPlanCache.get(macAddress);
        }
        return Optional.ofNullable(belongToUavRawDataFlightPlan);
    }

    @Scheduled(fixedDelay = 60000 * 15)
    @Override
    public void scanAndRemoveExpiredPlan() {
        proceedingFlightPlanCache.values().removeIf(plan -> {
            LocalTime endTime = DateTimeUtils.convertToLocalTime(plan.getEndTime());
            LocalTime now = DateTimeUtils.getCurrentLocalTime();
            return endTime.isAfter(now);
        });
    }


    @Override
    public List<FlightPlan> getProceedingAndExpireFlightPlansByDate(String pilotAccount, String date) {
        return null;
    }

    @Override
    public List<TrajectoryPoint> getProceedingAndExpireFlightTrajectoryByDate(String pilotAccount, String date) {

        return null;
    }

    @Override
    public void predictTrajectoryAndStability(List<TrajectoryPoint> trajectory) {

    }
}
