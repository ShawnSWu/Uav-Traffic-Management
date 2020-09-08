package com.nutn.utm.exception;

import com.nutn.utm.model.dto.response.error.ErrorFieldDto;
import com.nutn.utm.model.dto.response.error.NotFoundFlightPlanResponse;
import com.nutn.utm.model.dto.response.error.RequestErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = NotFoundFlightPlanException.class)
    @ResponseBody
    public ResponseEntity<?> handleNotFound(NotFoundFlightPlanException e) {
        return new ResponseEntity<>(new NotFoundFlightPlanResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    @ResponseBody
    public ResponseEntity<?> handleInvalidRequest(InvalidRequestException e) {
        List<ErrorFieldDto> errorFieldList = new ArrayList<>();
        List<FieldError> fieldErrors = e.getErrors();
        fieldErrors.forEach(fieldError -> {
            errorFieldList.add(new ErrorFieldDto(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        RequestErrorResponseDto errorResponse = new RequestErrorResponseDto(errorFieldList, "Invalid Request");
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
