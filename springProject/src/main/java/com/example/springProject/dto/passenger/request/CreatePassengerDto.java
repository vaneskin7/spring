package com.example.springProject.dto.passenger.request;

import jakarta.validation.constraints.*;

public record CreatePassengerDto (
    @NotBlank(message = "Введите корректное имя")
    @Size(min = 2)
    String name,

    @Email
    @NotBlank(message = "Введите корректный email")
    String email,

    @NotBlank(message = "Введите корректный номер")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$")
    String phoneNumber
) {}
