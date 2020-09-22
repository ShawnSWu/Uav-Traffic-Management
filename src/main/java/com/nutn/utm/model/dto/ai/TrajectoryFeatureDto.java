package com.nutn.utm.model.dto.ai;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@JsonPropertyOrder({
        "latitude",
        "longitude",
        "windDegree",
        "windSpeed"
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrajectoryFeatureDto {

    private double latitude;
    private double longitude;
    private int windDegree;
    private int windSpeed;
}
