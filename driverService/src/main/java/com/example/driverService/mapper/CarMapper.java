package com.example.driverService.mapper;

import com.example.driverService.dto.car.request.CreateCarDto;
import com.example.driverService.dto.car.request.UpdateCarDto;
import com.example.driverService.dto.car.response.CarResponseDto;
import com.example.driverService.entity.Car;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(source = "driver.id", target = "driverId")
    CarResponseDto toDto(Car entity);
    Car toEntity(CreateCarDto dto);
    List<CarResponseDto> toDtoList(List<Car> entityList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCarFromDto(UpdateCarDto dto, @MappingTarget Car car);
}
