package com.example.springProject.mapper;

import com.example.springProject.dto.passenger.request.CreatePassengerDto;
import com.example.springProject.dto.passenger.request.UpdatePassengerDto;
import com.example.springProject.dto.passenger.response.PassengerResponseDto;
import com.example.springProject.entity.Passenger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-03T09:58:00+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class PassengerMapperImpl implements PassengerMapper {

    @Override
    public Passenger toEntity(CreatePassengerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Passenger passenger = new Passenger();

        passenger.setName( dto.name() );
        passenger.setEmail( dto.email() );
        passenger.setPhoneNumber( dto.phoneNumber() );

        return passenger;
    }

    @Override
    public PassengerResponseDto toDto(Passenger entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;
        String phoneNumber = null;

        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phoneNumber = entity.getPhoneNumber();

        PassengerResponseDto passengerResponseDto = new PassengerResponseDto( id, name, email, phoneNumber );

        return passengerResponseDto;
    }

    @Override
    public List<PassengerResponseDto> toDtoList(List<Passenger> entity) {
        if ( entity == null ) {
            return null;
        }

        List<PassengerResponseDto> list = new ArrayList<PassengerResponseDto>( entity.size() );
        for ( Passenger passenger : entity ) {
            list.add( toDto( passenger ) );
        }

        return list;
    }

    @Override
    public void updatePassengerFromDto(UpdatePassengerDto passengerDto, Passenger existingPassenger) {
        if ( passengerDto == null ) {
            return;
        }

        if ( passengerDto.name() != null ) {
            existingPassenger.setName( passengerDto.name() );
        }
        if ( passengerDto.email() != null ) {
            existingPassenger.setEmail( passengerDto.email() );
        }
        if ( passengerDto.phoneNumber() != null ) {
            existingPassenger.setPhoneNumber( passengerDto.phoneNumber() );
        }
    }
}
