package com.nutn.utm.model.dto.geojson.trajectory;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "type",
        "features"
})
public class FlightTrajectoryFeatureCollectionDto {

    private String type ;

    private List<FlightTrajectoryFeatureDto> features;
}
