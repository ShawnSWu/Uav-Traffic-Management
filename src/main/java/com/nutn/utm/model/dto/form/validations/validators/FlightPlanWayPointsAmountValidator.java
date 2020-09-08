package com.nutn.utm.model.dto.form.validations.validators;

import com.nutn.utm.model.dto.form.validations.annotations.MinCoordinateCount;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanWayPointsDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class FlightPlanWayPointsAmountValidator implements ConstraintValidator<MinCoordinateCount, FlightPlanWayPointsDto> {

    private int count;

    @Override
    public void initialize(MinCoordinateCount minCoordinateCount) {
        count = minCoordinateCount.count();
    }

    @Override
    public boolean isValid(FlightPlanWayPointsDto flightPlanWayPointsDTO, ConstraintValidatorContext constraintValidatorContext) {
        return flightPlanWayPointsDTO.getCoordinate().length >= count;
    }
}
