package com.example.tripService.repository;

import com.example.tripService.entity.Trip;
import com.example.tripService.enums.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("SELECT COUNT(t) > 0 FROM Trip t WHERE (t.driverId =:driverId OR t.passengerId = :passengerId) AND status =:status")
    boolean existsByDriverIdOrPassengerIdAndStatus(
            @Param("driverId") Long driverId, @Param("passengerId") Long passengerId, @Param("status")TripStatus status);
}
