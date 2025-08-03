package com.example.springProject.controller;

import com.example.springProject.dto.passenger.request.CreatePassengerDto;
import com.example.springProject.dto.passenger.request.UpdatePassengerDto;
import com.example.springProject.dto.passenger.response.PassengerResponseDto;
import com.example.springProject.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    private static final String ID_PATH = "/{id}";

    @GetMapping
    public List<PassengerResponseDto> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping(ID_PATH)
    public PassengerResponseDto getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassengerResponseDto createPassenger(@Valid @RequestBody CreatePassengerDto passengerDto) {
        return passengerService.createPassenger(passengerDto);
    }

    @DeleteMapping(ID_PATH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassengerById(@PathVariable Long id) {
        passengerService.deletePassengerById(id);
    }

    @PatchMapping(ID_PATH)
    public PassengerResponseDto updatePassenger(@PathVariable Long id, @Valid @RequestBody UpdatePassengerDto passengerDto) {
        return passengerService.updatePassenger(id, passengerDto);
    }
}
