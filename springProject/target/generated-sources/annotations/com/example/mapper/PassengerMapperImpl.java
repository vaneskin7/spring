package com.example.mapper;

import com.example.springProject.dto.passenger.request.CreatePassengerDto;
import com.example.springProject.entity.Passenger;
import com.example.springProject.mapper.PassengerMapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-29T12:34:10+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
public class PassengerMapperImpl implements PassengerMapper {

    @Override
    public Passenger toEntity(CreatePassengerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Passenger passenger = new Passenger();

        return passenger;
    }

    @Override
    public CreatePassengerDto toDto(Passenger entity) {
        if ( entity == null ) {
            return null;
        }

        CreatePassengerDto passengerDto = new CreatePassengerDto();

        return passengerDto;
    }

    @Override
    public List<Passenger> toEntityList(List<CreatePassengerDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Passenger> list = new ArrayList<Passenger>( dto.size() );
        for ( CreatePassengerDto passengerDto : dto ) {
            list.add( toEntity( passengerDto ) );
        }

        return list;
    }

    @Override
    public List<CreatePassengerDto> toDtoList(List<Passenger> entity) {
        if ( entity == null ) {
            return null;
        }

        List<CreatePassengerDto> list = new ArrayList<CreatePassengerDto>( entity.size() );
        for ( Passenger passenger : entity ) {
            list.add( toDto( passenger ) );
        }

        return list;
    }
}
