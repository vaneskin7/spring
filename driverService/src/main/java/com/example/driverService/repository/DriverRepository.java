package com.example.driverService.repository;

import com.example.driverService.entity.Driver;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Override
    @EntityGraph(attributePaths = "cars")
    List<Driver> findAll();

    @Override
    @EntityGraph(attributePaths = "cars")
    Optional<Driver> findById(Long id);
}
