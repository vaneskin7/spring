package com.example.tripService.exception;

import com.example.tripService.dto.error.ErrorResponseDto;
import com.example.tripService.dto.error.ValidationErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TripNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleTripNotFound(TripNotFoundException exception) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponseDto handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ValidationErrorResponseDto(HttpStatus.BAD_REQUEST.value(), "Validation error", LocalDateTime.now(), errors);
    }

    @ExceptionHandler(DriverNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleDriverNotFoundExceptions(DriverNotFoundException exception) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(PassengerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handlePassengerNotFoundExceptions(PassengerNotFoundException exception) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(TripAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleTripAlreadyExistsExceptions(TripAlreadyExistsException exception) {
        return new ErrorResponseDto(HttpStatus.CONFLICT.value(), exception.getMessage(), LocalDateTime.now());
    }
}
