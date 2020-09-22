package com.nutn.utm.model.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDataDto {

    @JsonProperty("current")
    private CurrentWeatherData currentWeatherData;

}

