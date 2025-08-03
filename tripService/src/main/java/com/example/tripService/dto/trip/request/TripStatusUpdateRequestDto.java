package com.example.tripService.dto.trip.request;

import com.example.tripService.enums.TripStatus;
import jakarta.validation.constraints.NotNull;

public record TripStatusUpdateRequestDto (
    @NotNull
    TripStatus status
) {}
