package com.example.tripService.mapper;

import com.example.tripService.dto.trip.request.TripCreateRequestDto;
import com.example.tripService.dto.trip.request.TripUpdateRequestDto;
import com.example.tripService.dto.trip.response.TripResponseDto;
import com.example.tripService.entity.Trip;
import com.example.tripService.enums.TripStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-01T17:09:53+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class TripMapperImpl implements TripMapper {

    @Override
    public TripResponseDto toDto(Trip entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        Long driverId = null;
        Long passengerId = null;
        String departureLocation = null;
        String arrivalLocation = null;
        TripStatus status = null;
        LocalDateTime timestamp = null;
        BigDecimal price = null;

        id = entity.getId();
        driverId = entity.getDriverId();
        passengerId = entity.getPassengerId();
        departureLocation = entity.getDepartureLocation();
        arrivalLocation = entity.getArrivalLocation();
        status = entity.getStatus();
        timestamp = entity.getTimestamp();
        price = entity.getPrice();

        TripResponseDto tripResponseDto = new TripResponseDto( id, driverId, passengerId, departureLocation, arrivalLocation, status, timestamp, price );

        return tripResponseDto;
    }

    @Override
    public List<TripResponseDto> toDtoList(List<Trip> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TripResponseDto> list = new ArrayList<TripResponseDto>( entityList.size() );
        for ( Trip trip : entityList ) {
            list.add( toDto( trip ) );
        }

        return list;
    }

    @Override
    public Trip toEntity(TripCreateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Trip trip = new Trip();

        trip.setDriverId( dto.driverId() );
        trip.setPassengerId( dto.passengerId() );
        trip.setDepartureLocation( dto.departureLocation() );
        trip.setArrivalLocation( dto.arrivalLocation() );
        trip.setStatus( dto.status() );
        trip.setPrice( dto.price() );

        return trip;
    }

    @Override
    public void updateEntityFromDto(TripUpdateRequestDto dto, Trip entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.driverId() != null ) {
            entity.setDriverId( dto.driverId() );
        }
        if ( dto.passengerId() != null ) {
            entity.setPassengerId( dto.passengerId() );
        }
        if ( dto.departureLocation() != null ) {
            entity.setDepartureLocation( dto.departureLocation() );
        }
        if ( dto.arrivalLocation() != null ) {
            entity.setArrivalLocation( dto.arrivalLocation() );
        }
        if ( dto.status() != null ) {
            entity.setStatus( dto.status() );
        }
        if ( dto.price() != null ) {
            entity.setPrice( dto.price() );
        }
    }
}
