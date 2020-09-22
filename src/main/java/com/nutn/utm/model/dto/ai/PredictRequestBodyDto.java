package com.nutn.utm.model.dto.ai;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@JsonPropertyOrder({
        "planId",
        "trajectoryFeature"
})
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PredictRequestBodyDto {

    @JsonProperty("planId")
    private Long planId;

    @JsonProperty("trajectoryFeature")
    private List<TrajectoryFeatureDto> trajectoryFeature;



}
