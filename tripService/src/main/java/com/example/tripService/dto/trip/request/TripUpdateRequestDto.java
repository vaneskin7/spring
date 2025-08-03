package com.example.tripService.dto.trip.request;

import com.example.tripService.enums.TripStatus;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;

public record TripUpdateRequestDto(
    Long driverId,
    Long passengerId,
    String departureLocation,
    String arrivalLocation,
    TripStatus status,

    @Min(value = 0)
    BigDecimal price
) {}
