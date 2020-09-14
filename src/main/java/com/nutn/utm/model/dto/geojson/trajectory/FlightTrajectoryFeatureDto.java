package com.nutn.utm.model.dto.geojson.trajectory;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "type",
        "geometry",
        "properties"
})
public class FlightTrajectoryFeatureDto{

    private String type;

    private FlightTrajectoryGeometryDto geometry;

    private FlightTrajectoryPropertiesDto properties;
}
