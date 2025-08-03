package com.example.springProject.dto.errors;

import java.time.LocalDateTime;

public record ErrorResponseDto (
    int status,
    String message,
    LocalDateTime timeStamp
) {}
