package com.example.tripService.dto.trip.request;

import com.example.tripService.enums.TripStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TripCreateRequestDto (

    @NotNull(message = "Некорректный ID водителя")
    Long driverId,

    @NotNull(message = "Некорренктный ID пассажира")
    Long passengerId,

    @NotBlank(message = "Введите точку отправления")
    String departureLocation,

    @NotBlank(message = "Введите точку назначения")
    String arrivalLocation,

    @NotNull(message = "Укажите корректный статус поездки")
    TripStatus status,

    @Min(value = 0)
    @NotNull(message = "Введите стоимость поездки")
    BigDecimal price
) {}
