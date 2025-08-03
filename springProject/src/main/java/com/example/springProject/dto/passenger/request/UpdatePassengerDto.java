package com.example.springProject.dto.passenger.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdatePassengerDto (
    @Size(min = 2, message = "Имя минимум 2 символа")
    String name,

    @Email(message = "Некорректный email")
    String email,

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Неверный формат номера телефона")
    String phoneNumber
) {}
