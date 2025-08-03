package com.example.springProject.exception;

import com.example.springProject.dto.errors.ErrorResponseDto;
import com.example.springProject.dto.errors.ValidationErrorResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
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
    @ExceptionHandler(PassengerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handlePassengerNotFound(PassengerNotFoundException exception) {
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

    @ExceptionHandler(PassengerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handlePassengerAlreadyExists(PassengerAlreadyExistsException exception) {
        return new ErrorResponseDto(HttpStatus.CONFLICT.value(), exception.getMessage(), LocalDateTime.now());
    }

    /*@ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        String message = "Пассажир с такими данными уже существует";
        return new ErrorResponseDto(HttpStatus.CONFLICT.value(), message, LocalDateTime.now());
    }*/
}
