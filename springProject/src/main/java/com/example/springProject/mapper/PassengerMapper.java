package com.example.springProject.mapper;

import com.example.springProject.dto.passenger.request.CreatePassengerDto;
import com.example.springProject.dto.passenger.request.UpdatePassengerDto;
import com.example.springProject.dto.passenger.response.PassengerResponseDto;
import com.example.springProject.entity.Passenger;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    Passenger toEntity(CreatePassengerDto dto);
    PassengerResponseDto toDto(Passenger entity);
    List<PassengerResponseDto> toDtoList(List<Passenger> entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePassengerFromDto(UpdatePassengerDto passengerDto, @MappingTarget Passenger existingPassenger);

}


