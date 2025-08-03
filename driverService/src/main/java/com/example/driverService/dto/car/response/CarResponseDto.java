package com.example.driverService.dto.car.response;

public record CarResponseDto (
    Long id,
    String brand,
    String color,
    String plateNumber,
    String driverId
) {}
