package com.example.driverService.mapper;

import com.example.driverService.dto.driver.request.CreateDriverDto;
import com.example.driverService.dto.driver.request.UpdateDriverDto;
import com.example.driverService.dto.driver.response.DriverResponseDto;
import com.example.driverService.entity.Driver;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = CarMapper.class)
public interface DriverMapper {
    DriverResponseDto toDto(Driver entity);
    Driver toEntity(CreateDriverDto dto);
    List<DriverResponseDto> toDtoList(List<Driver> entityList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDriverFromDto(UpdateDriverDto dto, @MappingTarget Driver driver);
}
