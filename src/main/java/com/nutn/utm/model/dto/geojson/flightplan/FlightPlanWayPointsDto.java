package com.nutn.utm.model.dto.geojson.flightplan;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
import java.io.IOException;

public class FlightPlanWayPointsDto {

    private double[][] coordinate;

    public FlightPlanWayPointsDto(){}

    public FlightPlanWayPointsDto(double[][] coordinate) {
        this.coordinate = coordinate;
    }

    public FlightPlanWayPointsDto(String pathJson) {
        try {
            FlightPlanWayPointsDto dto = new ObjectMapper().readValue(pathJson, FlightPlanWayPointsDto.class);
            this.coordinate = dto.getCoordinate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[][] getCoordinate() {
        return coordinate;
    }


}
