package com.nutn.utm.utility.geojson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutn.utm.model.PredictTrajectoryGeoJsonProperties;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanFeatureCollectionDto;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanFeatureDto;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanWayPointsDto;
import com.nutn.utm.model.dto.geojson.geography.GeographyLimitAreaFeatureCollection;
import com.nutn.utm.model.dto.geojson.trajectory.FlightTrajectoryFeatureCollectionDto;
import com.nutn.utm.model.dto.ai.TrajectoryFeatureDto;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.model.entity.FlightData;
import com.nutn.utm.utility.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Component
public class GeoJsonConverter {

    @Autowired
    ObjectMapper objectMapper;

    public FlightPlanFeatureCollectionDto convertFlightPlansToFeatureCollection(List<FlightPlan> flightPlans) {
        FeatureCollectionBuilder featureCollectionBuilder = GeoJsonTool.buildFeatureCollection();

        flightPlans.forEach(flightPlan -> {
            FlightPlanWayPointsDto flightPlanPath = new FlightPlanWayPointsDto(flightPlan.getFlightPlanWayPoints());
            double[][] planPoint = flightPlanPath.getCoordinate();
            List<List<Double>> lineStringPoint = new ArrayList<>();
            for (double[] point : planPoint)
                lineStringPoint.add(Arrays.asList(point[0], point[1]));

            Properties properties = createFlightPlanProperties(flightPlan, lineStringPoint);
            featureCollectionBuilder.addLineString(lineStringPoint, properties);
        });

        return featureCollectionBuilder.buildJsonObject(FlightPlanFeatureCollectionDto.class);
    }

    public FlightPlanFeatureDto convertFlightPlanToFeature(FlightPlan flightPlan) {
        FeatureBuilder featureBuilder = GeoJsonTool.buildFeature();
        if (flightPlan != null) {
            FlightPlanWayPointsDto flightPlanPath = new FlightPlanWayPointsDto(flightPlan.getFlightPlanWayPoints());

            double[][] planPoint = flightPlanPath.getCoordinate();
            List<List<Double>> lineStringPoint = new ArrayList<>();
            for (double[] point : planPoint)
                lineStringPoint.add(Arrays.asList(point[0], point[1]));

            Properties properties = createFlightPlanProperties(flightPlan, lineStringPoint);
            featureBuilder.buildLineString(lineStringPoint, properties);
        }

        return featureBuilder.buildJsonObject(FlightPlanFeatureDto.class);
    }

    private Properties createFlightPlanProperties(FlightPlan flightPlan, List<List<Double>> lineStringPoint) {
        List<Double> startPoint = lineStringPoint.get(0);
        List<Double> endPoint = lineStringPoint.get(lineStringPoint.size() - 1);
        List<String> startAndEndPoint = Arrays.asList(startPoint.toString(), endPoint.toString());

        Properties properties = new Properties();
        properties.setProperty("startDate", DateTimeUtils.convertDateToString(flightPlan.getExecutionDate()));
        properties.setProperty("macAddress", flightPlan.getUav().getMacAddress());
        properties.setProperty("maxFlyHeight", String.valueOf(flightPlan.getMaxFlyingAltitude()));
        properties.setProperty("planId", String.valueOf(flightPlan.getId()));
        properties.setProperty("startTime", DateTimeUtils.convertTimeToString(flightPlan.getStartTime()));
        properties.setProperty("endTime", DateTimeUtils.convertTimeToString(flightPlan.getEndTime()));
        properties.setProperty("description", flightPlan.getDescription());
        properties.setProperty("startPoint", startAndEndPoint.get(0));
        properties.setProperty("endPoint", startAndEndPoint.get(1));
        return properties;
    }

    public GeographyLimitAreaFeatureCollection convertAreaToGeographyCollection(byte[] area) {
        GeographyLimitAreaFeatureCollection geographyLimitAreaFeatureCollection = null;
        try {
            geographyLimitAreaFeatureCollection = objectMapper.readValue(area, GeographyLimitAreaFeatureCollection.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return geographyLimitAreaFeatureCollection;
    }

    private FeatureCollectionBuilder getFlightTrajectoryToFeatureCollection(Map<Long, List<FlightData>> executingFlightTrajectory) {
        FeatureCollectionBuilder featureCollectionBuilder = GeoJsonTool.buildFeatureCollection();
        executingFlightTrajectory.forEach(((planId, flightDataList) -> {
            if (!flightDataList.isEmpty()) {
                List<List<Double>> planFlightTrajectory = flightDataList.stream()
                        .map(flightData -> Arrays.asList(flightData.getLatitude(), flightData.getLongitude()))
                        .collect(Collectors.toList());

                Properties trajectoryProperties = createFlightTrajectoryProperties(planId, flightDataList);

                featureCollectionBuilder.addLineString(planFlightTrajectory, trajectoryProperties);
            }
        }));
        return featureCollectionBuilder;
    }

    public FlightTrajectoryFeatureCollectionDto convertFlightTrajectoryToFeatureCollection(Map<Long, List<FlightData>> executingFlightTrajectory) {
        return getFlightTrajectoryToFeatureCollection(executingFlightTrajectory).buildJsonObject(FlightTrajectoryFeatureCollectionDto.class);
    }

    public String convertFlightTrajectoryToString(Map<Long, List<FlightData>> executingFlightTrajectory) {
        return getFlightTrajectoryToFeatureCollection(executingFlightTrajectory).buildToString();
    }

    private Properties createFlightTrajectoryProperties(long planId, List<FlightData> flightDataList) {
        FlightData currentFlightData = flightDataList.get(flightDataList.size() - 1);

        Double currentLongitude = currentFlightData.getLongitude();
        Double currentLatitude = currentFlightData.getLatitude();

        Properties properties = new Properties();
        properties.setProperty("planId", String.valueOf(planId));
        properties.setProperty("macAddress", currentFlightData.getFlightPlan().getUav().getMacAddress());
        properties.setProperty("latestReceivingTime", DateTimeUtils.convertTimeToString(currentFlightData.getTime()));
        properties.setProperty("currentFlyAltitude", String.valueOf(currentFlightData.getAltitude()));
        properties.setProperty("currentLocation", String.format("[%s, %s]", currentLongitude, currentLatitude));
        return properties;
    }

    public String convertPredictFlightTrajectoryToString(PredictTrajectoryGeoJsonProperties properties, Map<Long, List<TrajectoryFeatureDto>> predictFlightTrajectory) {
        return getPredictFlightTrajectoryToFeatureCollection(properties, predictFlightTrajectory).buildToString();
    }

    private FeatureCollectionBuilder getPredictFlightTrajectoryToFeatureCollection(PredictTrajectoryGeoJsonProperties properties, Map<Long, List<TrajectoryFeatureDto>> executingFlightTrajectory) {
        FeatureCollectionBuilder featureCollectionBuilder = GeoJsonTool.buildFeatureCollection();
        executingFlightTrajectory.forEach(((planId, flightDataList) -> {
            if (!flightDataList.isEmpty()) {
                List<List<Double>> planFlightTrajectory = flightDataList.stream()
                        .map(flightData -> Arrays.asList(flightData.getLatitude(), flightData.getLongitude()))
                        .collect(Collectors.toList());

                Properties trajectoryProperties = createPredictFlightTrajectoryProperties(properties, flightDataList);

                featureCollectionBuilder.addLineString(planFlightTrajectory, trajectoryProperties);
            }
        }));
        return featureCollectionBuilder;
    }

    private Properties createPredictFlightTrajectoryProperties(PredictTrajectoryGeoJsonProperties predictTrajectoryGeoJsonProperties, List<TrajectoryFeatureDto> flightDataList) {
        TrajectoryFeatureDto predictTrajectoryFeatureDto = flightDataList.get(flightDataList.size() - 1);
        TrajectoryFeatureDto currentTrajectoryFeatureDto = flightDataList.get(flightDataList.size() - 2);

        Double predictLongitude = predictTrajectoryFeatureDto.getLongitude();
        Double predictLatitude = predictTrajectoryFeatureDto.getLatitude();

        Double currentLongitude = currentTrajectoryFeatureDto.getLongitude();
        Double currentLatitude = currentTrajectoryFeatureDto.getLatitude();

        Properties properties = new Properties();
        properties.setProperty("planId", predictTrajectoryGeoJsonProperties.getPlanId());
        properties.setProperty("macAddress", predictTrajectoryGeoJsonProperties.getMacAddress());
        properties.setProperty("latestReceivingTime", predictTrajectoryGeoJsonProperties.getLatestReceivingTime());
        properties.setProperty("currentFlyAltitude", predictTrajectoryGeoJsonProperties.getCurrentFlyAltitude());
        properties.setProperty("currentLocation", String.format("[%s, %s]", currentLongitude, currentLatitude));
        properties.setProperty("predictNextLocation", String.format("[%s, %s]", predictLongitude, predictLatitude));
        return properties;
    }


}
