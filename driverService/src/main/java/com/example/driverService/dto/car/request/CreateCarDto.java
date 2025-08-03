package com.example.driverService.dto.car.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateCarDto (

    @NotBlank(message =  "Введите марку машины")
    String brand,

    @NotBlank(message = "Введите цвет машины")
    String color,

    @NotBlank(message = "Введите номер машины")
    @Pattern(regexp = "^\\d{4}\\s[A-Z]{2}-\\d$", message = "Неверный формат номера. Пример: 1234 AB-7")
    String plateNumber
) {}
