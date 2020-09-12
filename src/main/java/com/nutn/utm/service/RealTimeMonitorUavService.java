package com.nutn.utm.service;

import com.nutn.utm.model.dto.mqtt.LoRaGatewayMessage;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.model.entity.TrajectoryPoint;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface RealTimeMonitorUavService {

    List<FlightPlan> getCurrentlyExecutingFlightPlans(long accountId);

    List<FlightPlan> getCurrentlyExecutedFlightPlans(long accountId);

    List<FlightPlan> getCurrentlyPrepareFlightPlans(long accountId);

    List<TrajectoryPoint> getCurrentlyExecutingFlightTrajectory(long accountId);

    List<TrajectoryPoint> getCurrentlyExecutedFlightTrajectory(long accountId);

    void receiveMqttBrokerMessage(String message);

    void scanAndRemoveExpiredPlan();

    void saveRealTimeUavRawData(LoRaGatewayMessage loRaGatewayMessage);

    void predictTrajectoryAndStability(List<TrajectoryPoint> trajectory);


}
