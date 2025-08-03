package com.example.driverService.dto.driver.response;

import com.example.driverService.dto.car.response.CarResponseDto;
import java.util.List;

public record DriverResponseDto (
    Long id,
    String name,
    String email,
    String phoneNumber,
    List<CarResponseDto> cars
) {}
