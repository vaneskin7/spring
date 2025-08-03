package com.example.driverService.service;

import com.example.driverService.dto.car.request.AssignDriverDto;
import com.example.driverService.dto.car.request.CreateCarDto;
import com.example.driverService.dto.car.request.UpdateCarDto;
import com.example.driverService.dto.car.response.CarResponseDto;

import java.util.List;

public interface CarService {
    List<CarResponseDto> getAllCars();
    CarResponseDto getCarById(Long id);
    CarResponseDto createCar(CreateCarDto dto);
    void deleteCarById(Long id);
    CarResponseDto updateCarById(UpdateCarDto dto, Long id);

    CarResponseDto assignDriverForCar(Long id, AssignDriverDto dto);
    CarResponseDto unAssignDriverForCar(Long id);
}
