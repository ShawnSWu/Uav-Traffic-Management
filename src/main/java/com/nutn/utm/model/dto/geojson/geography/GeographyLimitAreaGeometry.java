package com.nutn.utm.model.dto.geojson.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class GeographyLimitAreaGeometry {


    @JsonProperty("type")
    private String type;
    @JsonProperty("coordinates")
    private List<List<List<Double>>> coordinates = null;
}
