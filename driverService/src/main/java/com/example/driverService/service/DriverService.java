package com.example.driverService.service;

import com.example.driverService.dto.driver.request.CreateDriverDto;
import com.example.driverService.dto.driver.request.UpdateDriverDto;
import com.example.driverService.dto.driver.response.DriverResponseDto;

import java.util.List;

public interface DriverService {
    List<DriverResponseDto> getAllDrivers();
    DriverResponseDto getDriverById(Long id);
    DriverResponseDto createDriver(CreateDriverDto dto);
    void deleteDriverById(Long id);
    DriverResponseDto updateDriverById(Long id, UpdateDriverDto dto);
}
