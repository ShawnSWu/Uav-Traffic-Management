package com.nutn.utm.model.dto.geojson.flightplan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "features"
})
public class FlightPlanFeatureCollectionDto {

    private String type;

    private List<FlightPlanFeatureDto> features;

    public FlightPlanFeatureCollectionDto(String type, List<FlightPlanFeatureDto> features) {
        this.type = type;
        this.features = features;
    }

}
