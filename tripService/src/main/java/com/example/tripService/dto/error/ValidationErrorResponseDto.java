package com.example.tripService.dto.error;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponseDto (
    int status,
    String error,
    LocalDateTime timestamp,
    Map<String, String> fieldErrors

) {}
