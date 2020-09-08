package com.nutn.utm.model.dto.geojson.flightplan;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
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

    public void setType(String type) {
        this.type = type;
    }

    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public List<List<Double>> getCoordinates() {
        return coordinates;
    }
}
