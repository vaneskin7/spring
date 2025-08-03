package com.example.driverService.dto.driver.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateDriverDto (
    @Size(min = 10, message = "Введите корректное имя")
    String name,

    @Email(message = "Введите корректный email")
    String email,

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Неверный формат номера телефона")
    String phoneNumber
) {}
