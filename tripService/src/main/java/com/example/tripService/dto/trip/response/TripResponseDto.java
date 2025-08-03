package com.example.tripService.dto.trip.response;

import com.example.tripService.enums.TripStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TripResponseDto(
    Long id,
    Long driverId,
    Long passengerId,
    String departureLocation,
    String arrivalLocation,
    TripStatus status,
    LocalDateTime timestamp,
    BigDecimal price
) {}
