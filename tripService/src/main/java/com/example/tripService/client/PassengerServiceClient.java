package com.example.tripService.client;

import com.example.tripService.dto.client.PassengerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "passenger-service", url = "${client.passenger-service.url}")
public interface PassengerServiceClient {
    @GetMapping("passengers/{id}")
    PassengerResponseDto getPassengerById(@PathVariable("id") Long id);
}
