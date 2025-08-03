package com.example.driverService.dto.error;

import java.time.LocalDateTime;

public record ErrorResponseDto (
    int status,
    String message,
    LocalDateTime timeStamp
) {}
