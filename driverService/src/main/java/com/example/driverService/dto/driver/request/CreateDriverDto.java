package com.example.driverService.dto.driver.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateDriverDto (
    @Size(min = 10)
    @NotBlank(message = "Введите корректное имя")
    String name,

    @Email
    @NotBlank(message = "Введите корректный email")
    String email,

    @NotBlank(message = "Введите корректный номер")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Неверный формат номера телефона")
    String phoneNumber
) {}
