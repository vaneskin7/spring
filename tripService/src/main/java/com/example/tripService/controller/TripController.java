package com.example.tripService.controller;

import com.example.tripService.dto.trip.request.TripCreateRequestDto;
import com.example.tripService.dto.trip.request.TripStatusUpdateRequestDto;
import com.example.tripService.dto.trip.request.TripUpdateRequestDto;
import com.example.tripService.dto.trip.response.TripResponseDto;
import com.example.tripService.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    private final static String ID_PATH = "/{id}";

    @GetMapping
    public List<TripResponseDto> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping(ID_PATH)
    public TripResponseDto getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TripResponseDto createTrip(@Valid @RequestBody TripCreateRequestDto dto) {
        return tripService.createTrip(dto);
    }

    @PatchMapping(ID_PATH)
    public TripResponseDto updateTrip(@PathVariable Long id, @Valid @RequestBody TripUpdateRequestDto dto) {
        return tripService.updateTripById(id, dto);
    }

    @PatchMapping(ID_PATH + "/status")
    public TripResponseDto updateTripStatus(@PathVariable Long id, @Valid @RequestBody TripStatusUpdateRequestDto dto) {
        return tripService.updateTripStatusById(id, dto);
    }

    @DeleteMapping(ID_PATH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTripById(id);
    }

}
