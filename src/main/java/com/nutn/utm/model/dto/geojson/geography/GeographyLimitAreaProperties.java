package com.nutn.utm.model.dto.geojson.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Getter
@JsonPropertyOrder({
        "description"
})
public class GeographyLimitAreaProperties {

    @JsonProperty("description")
    private String description;
}
