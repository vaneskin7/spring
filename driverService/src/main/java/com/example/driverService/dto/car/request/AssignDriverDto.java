package com.example.driverService.dto.car.request;

import jakarta.validation.constraints.NotNull;

public record AssignDriverDto (
        @NotNull(message = "Введите ID водителя")
    Long driverId
) {}
