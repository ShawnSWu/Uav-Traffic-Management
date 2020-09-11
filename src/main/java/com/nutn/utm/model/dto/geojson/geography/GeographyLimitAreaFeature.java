package com.nutn.utm.model.dto.geojson.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class GeographyLimitAreaFeature {

    @JsonProperty("type")
    private String type;
    @JsonProperty("geometry")
    private GeographyLimitAreaGeometry geometry;
    @JsonProperty("properties")
    private GeographyLimitAreaProperties properties;

}
