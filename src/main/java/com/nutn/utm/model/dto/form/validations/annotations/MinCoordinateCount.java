package com.nutn.utm.model.dto.form.validations.annotations;

import com.nutn.utm.model.dto.form.FormValidationMessage;
import com.nutn.utm.model.dto.form.validations.validators.FlightPlanWayPointsAmountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= FlightPlanWayPointsAmountValidator.class)
public @interface MinCoordinateCount {

    String message() default FormValidationMessage.FLIGHT_PATH_POINT_LESS_THEN_SPECIFIED_COUNT;

    int count();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
