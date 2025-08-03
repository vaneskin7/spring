package com.example.driverService.dto.error;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponseDto (
     int status,
     String error,
     LocalDateTime timestamp,
     Map<String, String> fieldErrors
) {}
