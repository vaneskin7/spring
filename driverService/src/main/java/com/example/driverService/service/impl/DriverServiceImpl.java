package com.example.driverService.service.impl;

import com.example.driverService.dto.driver.request.CreateDriverDto;
import com.example.driverService.dto.driver.request.UpdateDriverDto;
import com.example.driverService.dto.driver.response.DriverResponseDto;
import com.example.driverService.entity.Car;
import com.example.driverService.entity.Driver;
import com.example.driverService.exception.DriverAlreadyExistsException;
import com.example.driverService.exception.DriverNotFoundException;
import com.example.driverService.mapper.DriverMapper;
import com.example.driverService.repository.DriverRepository;
import com.example.driverService.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper mapper;

    private final static String DRIVER_NOT_FOUND_MESSAGE = "Водитель не найден";
    private final static String EMAIL_OR_PRONE_ALREADY_EXISTS_MESSAGE = "Данный email или телефонный номер уже занят";

    @Override
    public List<DriverResponseDto> getAllDrivers() {
        return mapper.toDtoList(driverRepository.findAll());
    }

    @Override
    public DriverResponseDto getDriverById(Long id) {
        Driver driver = findDriverById(id);
        return mapper.toDto(driver);
    }

    @Transactional
    @Override
    public DriverResponseDto createDriver(CreateDriverDto dto) {
        Driver driver = mapper.toEntity(dto);
        try {
            Driver savedDriver = driverRepository.save(driver);
            return mapper.toDto(savedDriver);
        }
        catch (DataIntegrityViolationException exception) {
            throw new DriverAlreadyExistsException(EMAIL_OR_PRONE_ALREADY_EXISTS_MESSAGE);
        }

    }

    @Transactional
    @Override
    public void deleteDriverById(Long id) {
        Driver driver = findDriverById(id);
        for (Car car : new ArrayList<>(driver.getCars())) {
            car.setDriver(null);
        }
        driverRepository.deleteById(id);
    }

    @Transactional
    @Override
    public DriverResponseDto updateDriverById(Long id, UpdateDriverDto dto) {
        Driver driver = findDriverById(id);
        mapper.updateDriverFromDto(dto, driver);
        try {
            driverRepository.flush();
            return mapper.toDto(driverRepository.save(driver));
        }
        catch (DataIntegrityViolationException exception) {
            throw new DriverAlreadyExistsException(EMAIL_OR_PRONE_ALREADY_EXISTS_MESSAGE);
        }
    }

    private Driver findDriverById(Long id) {
        return driverRepository.findById(id).
                orElseThrow(() -> new DriverNotFoundException(DRIVER_NOT_FOUND_MESSAGE));
    }

}
