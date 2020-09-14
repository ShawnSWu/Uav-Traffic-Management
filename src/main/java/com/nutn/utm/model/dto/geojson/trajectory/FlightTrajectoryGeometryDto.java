package com.nutn.utm.model.dto.geojson.trajectory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FlightTrajectoryGeometryDto {

    private String type;

    private List<List<Double>> coordinates;


}
