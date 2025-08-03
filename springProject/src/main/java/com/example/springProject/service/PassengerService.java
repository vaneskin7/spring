package com.example.springProject.service;

import com.example.springProject.dto.passenger.request.CreatePassengerDto;
import com.example.springProject.dto.passenger.request.UpdatePassengerDto;
import com.example.springProject.dto.passenger.response.PassengerResponseDto;

import java.util.List;

public interface PassengerService {
    List<PassengerResponseDto> getAllPassengers();
    PassengerResponseDto createPassenger(CreatePassengerDto passengerDto);
    void deletePassengerById(Long id);
    PassengerResponseDto getPassengerById(Long id);
    PassengerResponseDto updatePassenger(Long id, UpdatePassengerDto passengerDto);
}
