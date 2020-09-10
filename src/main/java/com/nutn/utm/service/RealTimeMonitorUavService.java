package com.nutn.utm.service;

import com.nutn.utm.model.dto.mqtt.LoRaGatewayMessage;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.model.entity.TrajectoryPoint;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface RealTimeMonitorUavService {

    List<FlightPlan> getProceedingAndExpireFlightPlansByDate(String pilotAccount, String date);

    List<TrajectoryPoint> getProceedingAndExpireFlightTrajectoryByDate(String pilotAccount, String date);

    void receiveMqttBrokerMessage(String message);

    void scanAndRemoveExpiredPlan();

    void saveRealTimeUavRawData(LoRaGatewayMessage loRaGatewayMessage);

    void predictTrajectoryAndStability(List<TrajectoryPoint> trajectory);


}
