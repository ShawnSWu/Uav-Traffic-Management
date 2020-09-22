package com.nutn.utm.model.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Getter
@JsonPropertyOrder({
        "observation_time",
        "temperature",
        "weather_code",
        "wind_speed",
        "wind_degree",
        "weather_descriptions",
        "wind_dir",
        "pressure",
        "precip",
        "humidity",
        "cloudcover",
        "feelslike",
        "uv_index",
        "visibility",
        "is_day"
})
public class CurrentWeatherData {


    @JsonProperty("observation_time")
    private String observationTime;
    @JsonProperty("temperature")
    private Integer temperature;

    @JsonProperty("weather_code")
    private Integer weatherCode;

    @JsonProperty("wind_speed")
    private Integer windSpeed;

    @JsonProperty("wind_degree")
    private Integer windDegree;

    @JsonProperty("wind_dir")
    private String windDir;

    @JsonProperty("weather_descriptions")
    private List<String> weatherDescriptions = null;

    @JsonProperty("pressure")
    private Integer pressure;

    @JsonProperty("precip")
    private Double precip;

    @JsonProperty("humidity")
    private Integer humidity;

    @JsonProperty("cloudcover")
    private Integer cloudcover;

    @JsonProperty("feelslike")
    private Integer feelslike;

    @JsonProperty("uv_index")
    private Integer uvIndex;

    @JsonProperty("visibility")
    private Integer visibility;
    @JsonProperty("is_day")
    private String isDay;

}
