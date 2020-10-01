package com.nutn.utm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutn.utm.model.UavSensingData;
import com.nutn.utm.model.dto.ai.PredictRequestBodyDto;
import com.nutn.utm.model.dto.ai.PredictResultListDto;
import com.nutn.utm.model.dto.mqtt.LoRaGatewayMessage;
import com.nutn.utm.model.dto.ai.TrajectoryFeatureDto;
import com.nutn.utm.model.dto.trajectory.TrajectoryAndPredictResultDto;
import com.nutn.utm.model.dto.weather.WeatherDataDto;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.model.entity.FlightData;
import com.nutn.utm.repository.FlightPlanRepository;
import com.nutn.utm.repository.FlightDataRepository;
import com.nutn.utm.service.mqtt.MqttMessagePublisher;
import com.nutn.utm.utility.DateTimeUtils;
import com.nutn.utm.utility.cayenne.CayenneLPPDataParser;
import com.nutn.utm.utility.geojson.GeoJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Service
public class RealTimeMonitorUavServiceImpl implements RealTimeMonitorUavService {

    private WeakHashMap<String, FlightPlan> proceedingFlightPlanCache = new WeakHashMap<>();

    @Autowired
    private FlightPlanService flightPlanService;

    @Autowired
    private FlightPlanRepository flightPlanRepository;

    @Autowired
    private FlightDataRepository flightDataRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MqttMessagePublisher mqttMessagePublisher;

    @Autowired
    GeoJsonConverter geoJsonConverter;

    @Autowired
    RestTemplate restTemplate;

    private final int aiTimeStep = 4;


    @Override
    public List<FlightPlan> getCurrentlyExecutingFlightPlans(long accountId) {
        Date today = DateTimeUtils.getTodayDate();
        Date currentTime = DateTimeUtils.getCurrentTime();
        return flightPlanRepository.findAllByPilotIdAndExecutionDateAndBetweenStartAndEndTime(
                accountId, today, currentTime);
    }

    @Override
    public List<FlightPlan> getCurrentlyExecutedFlightPlans(long accountId) {
        Date today = DateTimeUtils.getTodayDate();
        Date currentTime = DateTimeUtils.getCurrentTime();
        return flightPlanRepository.findAllByUavPilotIdAndExecutionDateAndEndTimeLessThanEqual(
                accountId, today, currentTime);
    }

    @Override
    public List<FlightPlan> getCurrentlyPrepareFlightPlans(long accountId) {
        Date today = DateTimeUtils.getTodayDate();
        Date currentTime = DateTimeUtils.getCurrentTime();
        return flightPlanRepository.findAllByUavPilotIdAndExecutionDateAndStartTimeGreaterThan(
                accountId, today, currentTime);
    }

    @Override
    public Map<Long, List<FlightData>> getCurrentlyExecutingFlightTrajectory(long accountId) {
        List<FlightPlan> currentlyExecutingFlightPlan = getCurrentlyExecutingFlightPlans(accountId);
        return currentlyExecutingFlightPlan.stream().collect(Collectors.toMap(FlightPlan::getId, FlightPlan::getFlightData));
    }

    @Override
    public Map<Long, List<FlightData>> getCurrentlyExecutedFlightTrajectory(long accountId) {
        List<FlightPlan> currentlyExecutedFlightPlan = getCurrentlyExecutedFlightPlans(accountId);
        return currentlyExecutedFlightPlan.stream().collect(Collectors.toMap(FlightPlan::getId, FlightPlan::getFlightData));

    }

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

    private Optional<FlightPlan> findFlightPlanAccordingToMacAddressAndTime(String macAddress, String date, String time) {
        FlightPlan belongToUavRawDataFlightPlan;
        if (!proceedingFlightPlanCache.containsKey(macAddress)) {
            belongToUavRawDataFlightPlan = flightPlanService.getFlightPlanBelongToUavRawData(macAddress, date, time);
            if (Optional.ofNullable(belongToUavRawDataFlightPlan).isPresent())
                proceedingFlightPlanCache.put(macAddress, belongToUavRawDataFlightPlan);
        } else {
            belongToUavRawDataFlightPlan = proceedingFlightPlanCache.get(macAddress);
        }
        return Optional.ofNullable(belongToUavRawDataFlightPlan);
    }


    @Override
    public void saveRealTimeUavRawData(LoRaGatewayMessage loRaGatewayMessage) {
        UavSensingData uavSensingData = CayenneLPPDataParser.parser(loRaGatewayMessage.getData());
        String macAddress = loRaGatewayMessage.getMacAddr();
        String date = LocalDate.now().toString();
        String time = LocalTime.now().withNano(0).toString();

        Optional<FlightPlan> belongToUavRawDataPlan = findFlightPlanAccordingToMacAddressAndTime(macAddress, date, time);
        belongToUavRawDataPlan.ifPresent(plan -> {
            FlightData flightData = FlightData.builder()
                    .date(DateTimeUtils.convertToDate(date))
                    .time(DateTimeUtils.convertToTime(time))
                    .latitude(uavSensingData.getGps().getLatitude())
                    .longitude(uavSensingData.getGps().getLongitude())
                    .altitude(uavSensingData.getGps().getAltitude())
                    .yaw(uavSensingData.getAttitude().getYaw())
                    .roll(uavSensingData.getAttitude().getRoll())
                    .pitch(uavSensingData.getAttitude().getPitch())
                    .gyroX(uavSensingData.getGyrometer().getX())
                    .gyroY(uavSensingData.getGyrometer().getY())
                    .gyroZ(uavSensingData.getGyrometer().getZ())
                    .accX(uavSensingData.getAccelerometer().getX())
                    .accY(uavSensingData.getAccelerometer().getY())
                    .accZ(uavSensingData.getAccelerometer().getZ())
                    .nedNorth(uavSensingData.getNedCoordinate().getNorth())
                    .nedDown(uavSensingData.getNedCoordinate().getEast())
                    .nedEast(uavSensingData.getNedCoordinate().getEast())
                    .flightPlan(plan)
                    .hex_data_packet(loRaGatewayMessage.getData()).build();
            flightDataRepository.save(flightData);
            long accountId = proceedingFlightPlanCache.get(macAddress).getUav().getPilot().getId();
            pushNewTrajectoryToMqttBroker(accountId);
        });
    }

    @Override
    public TrajectoryAndPredictResultDto getCurrentlyTrajectoryWithPrediction(long accountId) {
        Map<Long, List<FlightData>> executingFlightTrajectory = getCurrentlyExecutingFlightTrajectory(accountId);
        Map<Long, TrajectoryFeatureDto> predictionResult = predictUavTrajectory(executingFlightTrajectory);
        return TrajectoryAndPredictResultDto.builder().executingFlightTrajectory(executingFlightTrajectory).predictionResult(predictionResult).build();
    }

    private void pushNewTrajectoryToMqttBroker(long accountId) {
        TrajectoryAndPredictResultDto trajectoryAndPredictResultDto = getCurrentlyTrajectoryWithPrediction(accountId);
        String predictTrajectory = geoJsonConverter.convertTrajectoryWithPredictionToString(trajectoryAndPredictResultDto.getPredictionResult(), trajectoryAndPredictResultDto.getExecutingFlightTrajectory());
        mqttMessagePublisher.sendToMqtt(String.format(UAV_REALTIME_TRAJECTORY_TOPIC, accountId), predictTrajectory);
    }


    private Map<Long, TrajectoryFeatureDto> predictUavTrajectory(Map<Long, List<FlightData>> executingTrajectory) {
        Map<Long, TrajectoryFeatureDto> predictResultMap = new HashMap<>();
        List<PredictRequestBodyDto> aiModelFeatureMap = combineWeatherAndTrajectoryToBecomeAiModelFeature(executingTrajectory);
        String aiModelServiceUrl = "http://mcn.nutn.edu.tw:5000/predict";
        ResponseEntity<PredictResultListDto> predictResponse = restTemplate.postForEntity(aiModelServiceUrl, aiModelFeatureMap, PredictResultListDto.class);

        Optional.of(predictResponse).ifPresent(responseEntity -> {
            Objects.requireNonNull(responseEntity.getBody()).getPredictResult().forEach(predictRequestBodyDto -> {
                predictResultMap.put(predictRequestBodyDto.getPlanId(), predictRequestBodyDto.getTrajectoryFeature().get(aiTimeStep));
            });
        });

        return predictResultMap;
    }

    private List<PredictRequestBodyDto> combineWeatherAndTrajectoryToBecomeAiModelFeature(Map<Long, List<FlightData>> executingTrajectory) {
        List<PredictRequestBodyDto> predictRequestBodyDtoList = new ArrayList<>();
        executingTrajectory.forEach((planId, trajectory) -> {
            if (trajectory.size() >= aiTimeStep) {
                List<FlightData> lastFour = trajectory.subList(trajectory.size() - aiTimeStep, trajectory.size());
                List<TrajectoryFeatureDto> lastFourWithWeatherData = new ArrayList<>();
                int windSpeed = 0;
                int windDegree = 0;
                for (FlightData flightData : lastFour) {
                    double latitude = flightData.getLatitude();
                    double longitude = flightData.getLongitude();
                    if (windSpeed == 0 & windDegree == 0) {
                        WeatherDataDto weatherDataDto = getWeatherDataByLocation(latitude, longitude);
                        windSpeed = weatherDataDto.getCurrentWeatherData().getWindSpeed();
                        windDegree = weatherDataDto.getCurrentWeatherData().getWindDegree();
                    }
                    TrajectoryFeatureDto modelFeatureDto = TrajectoryFeatureDto.builder()
                            .latitude(latitude)
                            .longitude(longitude)
                            .windDegree(windDegree)
                            .windSpeed(windSpeed)
                            .build();
                    lastFourWithWeatherData.add(modelFeatureDto);
                }
                PredictRequestBodyDto predictRequestBodyDto = PredictRequestBodyDto.builder()
                        .planId(planId)
                        .trajectoryFeature(lastFourWithWeatherData)
                        .build();
                predictRequestBodyDtoList.add(predictRequestBodyDto);
            }
        });
        return predictRequestBodyDtoList;
    }

    private WeatherDataDto getWeatherDataByLocation(double lat, double lon) {
        String location = String.format("%f,%f", lat, lon);
        String accessKey = "1f70bbf9a994dde20e0bd64d5d472ae2";
        String weatherDataApiUrl = "http://api.weatherstack.com/current?access_key=%s&query=%s";
        String url = String.format(weatherDataApiUrl, accessKey, location);
        ResponseEntity<WeatherDataDto> responseEntity = restTemplate.getForEntity(url, WeatherDataDto.class);
        return Optional.of(responseEntity).get().getBody();
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
}
