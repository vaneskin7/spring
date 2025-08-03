package com.example.tripService.mapper;

import com.example.tripService.dto.trip.request.TripCreateRequestDto;
import com.example.tripService.dto.trip.request.TripUpdateRequestDto;
import com.example.tripService.dto.trip.response.TripResponseDto;
import com.example.tripService.entity.Trip;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TripMapper {
    TripResponseDto toDto(Trip entity);
    List<TripResponseDto> toDtoList(List<Trip> entityList);
    Trip toEntity(TripCreateRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TripUpdateRequestDto dto, @MappingTarget Trip entity);

}
