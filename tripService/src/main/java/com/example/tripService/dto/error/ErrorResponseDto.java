package com.example.tripService.dto.error;

import java.time.LocalDateTime;

public record ErrorResponseDto (
    int status,
    String message,
    LocalDateTime timestamp
) {}
