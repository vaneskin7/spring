package com.example.driverService.controller;

import com.example.driverService.dto.driver.request.CreateDriverDto;
import com.example.driverService.dto.driver.request.UpdateDriverDto;
import com.example.driverService.dto.driver.response.DriverResponseDto;
import com.example.driverService.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    private final static String ID_PATH = "/{id}";

    @GetMapping
    public List<DriverResponseDto> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping(ID_PATH)
    public DriverResponseDto getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id);
    }

    @PostMapping
    public DriverResponseDto createDriver(@Valid @RequestBody CreateDriverDto createDriverDto) {
        return driverService.createDriver(createDriverDto);
    }

    @PatchMapping(ID_PATH)
    public DriverResponseDto updateDriverById(@PathVariable Long id, @Valid @RequestBody UpdateDriverDto dto) {
        return driverService.updateDriverById(id, dto);
    }

    @DeleteMapping(ID_PATH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDriverById(@PathVariable Long id) {
        driverService.deleteDriverById(id);
    }
}
