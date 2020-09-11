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
        "features"
})
public class GeographyLimitAreaFeatureCollection {

    @JsonProperty("type")
    private String type;
    @JsonProperty("features")
    private List<GeographyLimitAreaFeature> features = null;
}
