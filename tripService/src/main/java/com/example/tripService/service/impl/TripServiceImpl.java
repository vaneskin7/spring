package com.example.tripService.service.impl;

import com.example.tripService.client.DriverServiceClient;
import com.example.tripService.client.PassengerServiceClient;
import com.example.tripService.dto.trip.request.TripCreateRequestDto;
import com.example.tripService.dto.trip.request.TripStatusUpdateRequestDto;
import com.example.tripService.dto.trip.request.TripUpdateRequestDto;
import com.example.tripService.dto.trip.response.TripResponseDto;
import com.example.tripService.entity.Trip;
import com.example.tripService.enums.TripStatus;
import com.example.tripService.exception.DriverNotFoundException;
import com.example.tripService.exception.PassengerNotFoundException;
import com.example.tripService.exception.TripAlreadyExistsException;
import com.example.tripService.exception.TripNotFoundException;
import com.example.tripService.mapper.TripMapper;
import com.example.tripService.repository.TripRepository;
import com.example.tripService.service.TripService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final TripMapper mapper;

    private final PassengerServiceClient passengerServiceClient;
    private final DriverServiceClient driverServiceClient;

    private static final String DRIVER_NOT_FOUND_MESSAGE = "Водитель не найден";
    private static final String PASSENGER_NOT_FOUND_MESSAGE = "Пассажир не найден";
    private static final String DRIVER_SERVICE_UNAVAILABLE_MESSAGE = "Сервис водителей недоступен";
    private static final String PASSENGER_SERVICE_UNAVAILABLE_MESSAGE = "Сервис пассажиров недоступен";
    private static final String TRIP_ALREADY_EXISTS_MESSAGE = "Поездка уже существует";

    @Override
    public List<TripResponseDto> getAllTrips() {
        return mapper.toDtoList(tripRepository.findAll());
    }

    @Override
    public TripResponseDto getTripById(Long id) {
        return mapper.toDto(findTripById(id));
    }

    @Transactional
    @Override
    public TripResponseDto createTrip(TripCreateRequestDto dto) {
        if (tripRepository.existsByDriverIdOrPassengerIdAndStatus(dto.driverId(), dto.passengerId(), TripStatus.CREATED)) {
            throw new TripAlreadyExistsException(TRIP_ALREADY_EXISTS_MESSAGE);
        }
        validateEntities(dto.driverId(), dto.passengerId());
        Trip trip = mapper.toEntity(dto);
        trip.setTimestamp(LocalDateTime.now());
        return mapper.toDto(tripRepository.save(trip));
    }

    @Transactional
    @Override
    public void deleteTripById(Long id) {
        tripRepository.deleteById(id);
    }

    @Transactional
    @Override
    public TripResponseDto updateTripById(Long id, TripUpdateRequestDto dto) {
        if (dto.driverId() != null || dto.passengerId() != null) {
            validateEntities(dto.driverId(), dto.passengerId());
        }
        Trip trip = findTripById(id);
        mapper.updateEntityFromDto(dto, trip);
        return mapper.toDto(tripRepository.save(trip));
    }

    @Transactional
    @Override
    public TripResponseDto updateTripStatusById(Long id, TripStatusUpdateRequestDto dto) {
        Trip trip = findTripById(id);
        trip.setStatus(dto.status());
        return mapper.toDto(tripRepository.save(trip));
    }

    private Trip findTripById(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new TripNotFoundException("Поездка не найдена"));
    }

    private void validateEntities(Long driverId, Long passengerId) {
        if (driverId != null) {
            try {
                driverServiceClient.getDriverById(driverId);
            } catch (FeignException.NotFound ex) {
                throw new DriverNotFoundException(DRIVER_NOT_FOUND_MESSAGE);
            }
            catch (FeignException ex) {
                throw new DriverNotFoundException(DRIVER_SERVICE_UNAVAILABLE_MESSAGE);
            }
        }
        if (passengerId != null) {
            try {
                passengerServiceClient.getPassengerById(passengerId);
            } catch (FeignException.NotFound ex) {
                throw new PassengerNotFoundException(PASSENGER_NOT_FOUND_MESSAGE);
            }
            catch (FeignException ex) {
                throw new PassengerNotFoundException(PASSENGER_SERVICE_UNAVAILABLE_MESSAGE);
            }
        }
    }
}
