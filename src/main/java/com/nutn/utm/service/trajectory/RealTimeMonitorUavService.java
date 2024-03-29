package com.nutn.utm.service.trajectory;

import com.nutn.utm.model.dto.mqtt.LoRaGatewayMessage;
import com.nutn.utm.model.dto.trajectory.TrajectoryAndPredictResultDto;
import com.nutn.utm.model.dto.trajectory.TrajectoryStabilityDto;
import com.nutn.utm.model.entity.FlightData;
import com.nutn.utm.model.entity.FlightPlan;

import java.util.List;
import java.util.Map;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface RealTimeMonitorUavService {

    String UAV_REALTIME_TRAJECTORY_TOPIC = "/realTime/uav/trajectory/%d";

    String UAV_STABILITY_NOTIFY_TOPIC = "/realTime/uav/stability/%d";

    String AI_MODEL_SERVER_URL = "http://mcn.nutn.edu.tw:5000/predict";

    List<FlightPlan> getCurrentlyExecutingFlightPlans(long accountId);

    List<FlightPlan> getCurrentlyExecutedFlightPlans(long accountId);

    List<FlightPlan> getCurrentlyPrepareFlightPlans(long accountId);

    Map<Long, List<FlightData>> getCurrentlyExecutingFlightTrajectory(long accountId);

    Map<Long, List<FlightData>> getCurrentlyExecutedFlightTrajectory(long accountId);

    void receiveMqttBrokerMessage(String message);

    void scanAndRemoveExpiredPlan();

    void saveRealTimeUavRawData(LoRaGatewayMessage loRaGatewayMessage);

    TrajectoryAndPredictResultDto getCurrentlyTrajectoryWithPrediction(long accountId);

    List<TrajectoryStabilityDto> getExecutingTrajectoryStability(long accountId);

}
