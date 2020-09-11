package com.nutn.utm.model.dto.geojson.flightplan;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Getter
@JsonPropertyOrder({
        "type",
        "coordinates"
})
public class FlightPlanGeometryDto {

    private String type;

    private List<List<Double>> coordinates = null;

    public FlightPlanGeometryDto(String type, List<List<Double>> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
}
