package com.example.tripService.client;

import com.example.tripService.dto.client.DriverResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "driver-service", url = "${client.driver-service.url}")
public interface DriverServiceClient {

    @GetMapping("/drivers/{id}")
    DriverResponseDto getDriverById(@PathVariable("id") Long id);
}
