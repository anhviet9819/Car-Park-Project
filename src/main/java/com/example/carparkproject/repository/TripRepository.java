package com.example.carparkproject.repository;

import com.example.carparkproject.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    public List<Trip> findByDestinationAndCarType(String destination, String carType);
}
