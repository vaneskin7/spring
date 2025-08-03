package com.example.tripService.service;

import com.example.tripService.dto.trip.request.TripCreateRequestDto;
import com.example.tripService.dto.trip.request.TripStatusUpdateRequestDto;
import com.example.tripService.dto.trip.request.TripUpdateRequestDto;
import com.example.tripService.dto.trip.response.TripResponseDto;

import java.util.List;

public interface TripService {
    List<TripResponseDto> getAllTrips();
    TripResponseDto getTripById(Long id);
    TripResponseDto createTrip(TripCreateRequestDto dto);
    void deleteTripById(Long id);
    TripResponseDto updateTripById(Long id, TripUpdateRequestDto dto);

    TripResponseDto updateTripStatusById(Long id, TripStatusUpdateRequestDto dto);
}
