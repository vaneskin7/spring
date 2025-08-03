package com.example.springProject.service.impl;

import com.example.springProject.dto.passenger.request.CreatePassengerDto;
import com.example.springProject.dto.passenger.request.UpdatePassengerDto;
import com.example.springProject.dto.passenger.response.PassengerResponseDto;
import com.example.springProject.entity.Passenger;
import com.example.springProject.exception.PassengerAlreadyExistsException;
import com.example.springProject.exception.PassengerNotFoundException;
import com.example.springProject.mapper.PassengerMapper;
import com.example.springProject.repository.PassengerRepository;
import com.example.springProject.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final  PassengerRepository passengerRepository;
    private final PassengerMapper mapper;

    private final static String PASSENGER_NOT_FOUND_MESSAGE = "пассажир не найден";
    private final static String EMAIL_OR_PRONE_ALREADY_EXISTS_MESSAGE = "Данный email или телефонный номер уже занят";

    @Override
    public List<PassengerResponseDto> getAllPassengers() {
        return mapper.toDtoList(passengerRepository.findAll());
    }

    @Override
    @Transactional
    public PassengerResponseDto createPassenger(CreatePassengerDto passengerDto) {
        Passenger passenger = mapper.toEntity(passengerDto);
        try {
            Passenger savedPassenger = passengerRepository.save(passenger);
            return mapper.toDto(savedPassenger);
        } catch (DataIntegrityViolationException e) {
            throw new PassengerAlreadyExistsException(EMAIL_OR_PRONE_ALREADY_EXISTS_MESSAGE);
        }
    }

    @Override
    @Transactional
    public void deletePassengerById(Long id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public PassengerResponseDto getPassengerById(Long id) {
        return mapper.toDto(findPassengerById(id));
    }

    @Override
    @Transactional
    public PassengerResponseDto updatePassenger(Long id, UpdatePassengerDto passengerDto) {
        Passenger existingPassenger = findPassengerById(id);
        mapper.updatePassengerFromDto(passengerDto, existingPassenger);
        try {
            Passenger updatedPassenger = passengerRepository.save(existingPassenger);
            passengerRepository.flush();
            return mapper.toDto(updatedPassenger);
        } catch (DataIntegrityViolationException e) {
            throw new PassengerAlreadyExistsException(EMAIL_OR_PRONE_ALREADY_EXISTS_MESSAGE);
        }
    }

    private Passenger findPassengerById(Long id) {
        return passengerRepository.findById(id).
                orElseThrow(() -> new PassengerNotFoundException(PASSENGER_NOT_FOUND_MESSAGE));
    }
}
