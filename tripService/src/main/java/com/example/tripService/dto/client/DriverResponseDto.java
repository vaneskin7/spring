package com.example.tripService.dto.client;

import java.util.List;

public record DriverResponseDto(
    Long id,
    String name,
    String email,
    String phoneNumber,
    List<CarResponseDto>cars
) { }
