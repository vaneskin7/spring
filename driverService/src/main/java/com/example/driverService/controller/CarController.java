package com.example.driverService.controller;

import com.example.driverService.dto.car.request.AssignDriverDto;
import com.example.driverService.dto.car.request.CreateCarDto;
import com.example.driverService.dto.car.request.UpdateCarDto;
import com.example.driverService.dto.car.response.CarResponseDto;
import com.example.driverService.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    private final static String ID_PATH = "/{id}";

    @GetMapping
    public List<CarResponseDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping(ID_PATH)
    public CarResponseDto getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public CarResponseDto createCar(@Valid @RequestBody CreateCarDto dto){
        return carService.createCar(dto);
    }

    @DeleteMapping(ID_PATH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
    }

    @PatchMapping(ID_PATH)
    public CarResponseDto updateCarById(@PathVariable Long id, @Valid @RequestBody UpdateCarDto dto) {
        return carService.updateCarById(dto, id);
    }

    @PatchMapping("/assign_driver" + ID_PATH)
    public CarResponseDto assignDriverForCar(@PathVariable Long id, @Valid @RequestBody AssignDriverDto dto) {
        return carService.assignDriverForCar(id, dto);
    }

    @PatchMapping("/unassign_driver" + ID_PATH)
    public CarResponseDto unAssignDriverForCar(@PathVariable Long id) {
        return carService.unAssignDriverForCar(id);
    }

}
