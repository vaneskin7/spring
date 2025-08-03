package com.example.driverService.service.impl;

import com.example.driverService.dto.car.request.AssignDriverDto;
import com.example.driverService.dto.car.request.CreateCarDto;
import com.example.driverService.dto.car.request.UpdateCarDto;
import com.example.driverService.dto.car.response.CarResponseDto;
import com.example.driverService.entity.Car;
import com.example.driverService.entity.Driver;
import com.example.driverService.exception.CarAlreadyExistsException;
import com.example.driverService.exception.CarNotFoundException;
import com.example.driverService.exception.DriverNotFoundException;
import com.example.driverService.mapper.CarMapper;
import com.example.driverService.repository.CarRepository;
import com.example.driverService.repository.DriverRepository;
import com.example.driverService.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final CarMapper mapper;

    private final static String PLATE_NUMBER_ALREADY_EXISTS_MESSAGE = "Номерной знак уже зарегистрирован";
    private final static String CAR_NOT_FOUND_MESSAGE = "Машина не найдена";
    private final static String DRIVER_NOT_FOUND_MESSAGE = "Водитель не найден";

    @Override
    public List<CarResponseDto> getAllCars() {
        return mapper.toDtoList(carRepository.findAll());
    }

    @Override
    public CarResponseDto getCarById(Long id) {
        Car car = findCarById(id);
        return mapper.toDto(car);
    }

    @Transactional
    @Override
    public CarResponseDto createCar(CreateCarDto dto) {
        Car car = mapper.toEntity(dto);
        try {
            return mapper.toDto(carRepository.save(car));
        } catch (DataIntegrityViolationException e) {
            throw new CarAlreadyExistsException(PLATE_NUMBER_ALREADY_EXISTS_MESSAGE);
        }
    }

    @Transactional
    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Transactional
    @Override
    public CarResponseDto updateCarById(UpdateCarDto dto, Long id) {
        Car car = findCarById(id);
        mapper.updateCarFromDto(dto, car);
        try {
            carRepository.flush();
            return mapper.toDto(carRepository.save(car));
        }
        catch (DataIntegrityViolationException exception) {
            throw new CarAlreadyExistsException(PLATE_NUMBER_ALREADY_EXISTS_MESSAGE);
        }
    }

    @Override
    @Transactional
    public CarResponseDto assignDriverForCar(Long id, AssignDriverDto dto) {
        Car car = findCarById(id);
        Driver driver = driverRepository.findById(dto.driverId()).
                orElseThrow(() -> new DriverNotFoundException(DRIVER_NOT_FOUND_MESSAGE));
        car.setDriver(driver);
        return mapper.toDto(car);
    }

    @Override
    @Transactional
    public CarResponseDto unAssignDriverForCar(Long id) {
        Car car = findCarById(id);
        car.setDriver(null);
        Car updatedCar = carRepository.save(car);
        return mapper.toDto(updatedCar);
    }

    private Car findCarById(Long id) {
        return carRepository.findById(id).
                orElseThrow(() -> new CarNotFoundException(CAR_NOT_FOUND_MESSAGE));
    }
}
