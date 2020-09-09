package com.nutn.utm.model.dto.geojson.trajectory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "features"
})
public class FlightTrajectoryFeatureCollectionDto {

//    private String type;
//
//    private List<FlightTrajectoryFeatureDto> features;
//
//    @JsonIgnore
//    public static FlightTrajectoryFeatureCollectionDto convert(List<FlightPlan> flightPlans){
//        FeatureCollectionBuilder pathFeatureCollection = GeoJsonTool.buildFeatureCollection();
//        flightPlans.forEach(flightPlan -> {
//            FlightPlanPathDTO flightPlanPath = new FlightPlanPathDTO(flightPlan.getFlightPlanPath());
//            double[][] planPoint = flightPlanPath.getCoordinate();
//            List<Point> lineStringPoint = new ArrayList<>();
//            for (double[] point : planPoint)
//                lineStringPoint.add(Point.fromLngLat(point[0], point[1]));
//
//            Point startPoint = lineStringPoint.get(0);
//            Point endPoint = lineStringPoint.get(lineStringPoint.size() - 1);
//            List<String> startAndEndPoint = Arrays.asList(startPoint.coordinates().toString(), endPoint.coordinates().toString());
//
//            Properties properties = new Properties();
//            properties.setProperty("expectedDate", DateTimeUtils.convertToDateString(flightPlan.getDate()));
//            properties.setProperty("uavMacAddress", flightPlan.getUav().getMacAddress());
//            properties.setProperty("flyHeight", String.valueOf(flightPlan.getExpectedFlyingHeight()));
//            properties.setProperty("planId", String.valueOf(flightPlan.getId()));
//            properties.setProperty("expectedTakeoffTime", DateTimeUtils.convertToTimeString(flightPlan.getExpectedTakeoffTime()));
//            properties.setProperty("expectedArrivalsTime", DateTimeUtils.convertToTimeString(flightPlan.getExpectedArrivalsTime()));
//            properties.setProperty("description", flightPlan.getFlightDescription());
//            properties.setProperty("startPoint", startAndEndPoint.get(0));
//            properties.setProperty("endPoint", startAndEndPoint.get(1));
//
//            pathFeatureCollection.addLineString(lineStringPoint, properties);
//        });
//        return pathFeatureCollection.buildJsonObject(FlightTrajectoryFeatureCollectionDto.class);
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public List<FlightTrajectoryFeatureDto> getFeatures() {
//        return features;
//    }
}
