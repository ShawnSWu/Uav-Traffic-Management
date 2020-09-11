package com.nutn.utm.model.dto.geojson.flightplan;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Getter
@JsonPropertyOrder({
        "type",
        "geometry",
        "properties"
})
public class FlightPlanFeatureDto {

    private String type;

    private FlightPlanGeometryDto geometry;

    private FlightPlanPropertiesDto properties;

    public FlightPlanFeatureDto(String type, FlightPlanGeometryDto geometry, FlightPlanPropertiesDto properties) {
        this.type = type;
        this.geometry = geometry;
        this.properties = properties;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGeometry(FlightPlanGeometryDto geometry) {
        this.geometry = geometry;
    }

    public void setProperties(FlightPlanPropertiesDto properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public FlightPlanGeometryDto getGeometry() {
        return geometry;
    }

    public FlightPlanPropertiesDto getProperties() {
        return properties;
    }
}
