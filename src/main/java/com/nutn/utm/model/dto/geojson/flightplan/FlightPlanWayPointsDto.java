package com.nutn.utm.model.dto.geojson.flightplan;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
import java.io.IOException;

@Getter
public class FlightPlanWayPointsDto {

    private Double[][] coordinate;

    public FlightPlanWayPointsDto(){}

    public FlightPlanWayPointsDto(Double[][] coordinate) {
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



}
