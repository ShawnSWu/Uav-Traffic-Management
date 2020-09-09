package com.nutn.utm.utility.geojson;

import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanFeatureCollectionDto;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanFeatureDto;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanWayPointsDto;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.utility.DateTimeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class GeoJsonConverter {

    public FlightPlanFeatureCollectionDto convertFlightPlansToFeatureCollection(List<FlightPlan> flightPlans){
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

    public FlightPlanFeatureDto convertFlightPlanToFeature(FlightPlan flightPlan){
        FeatureBuilder featureBuilder = GeoJsonTool.buildFeature();
        if (flightPlan!= null) {
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

    private Properties createFlightPlanProperties(FlightPlan flightPlan, List<List<Double>> lineStringPoint){
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
}
