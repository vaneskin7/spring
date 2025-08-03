package com.example.springProject.dto.passenger.response;

public record PassengerResponseDto (
    Long id,
    String name,
    String email,
    String phoneNumber
) {}
