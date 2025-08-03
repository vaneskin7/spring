package com.example.driverService.dto.car.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateCarDto (
    @Size(min = 3)
    String brand,

    @Size(min = 3)
    String color,

    @Pattern(regexp = "^\\d{4}\\s[A-Z]{2}-\\d$", message = "Неверный формат номера. Пример: 1234 AB-7")
    String plateNumber
) {}
