package com.example.tripService.dto.client;

public record PassengerResponseDto(
    Long id,
    String name,
    String email,
    String phoneNumber
) { }
