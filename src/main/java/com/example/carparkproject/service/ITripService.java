package com.example.carparkproject.service;

import com.example.carparkproject.entity.Car;
import com.example.carparkproject.entity.Trip;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITripService {
    public ResponseEntity<List<Trip>> getAll();
    public ResponseEntity<List<Trip>> getByDestinationAndCarType(String destination, String carType);
    public ResponseEntity<Trip> getById(Long id);
    public ResponseEntity<Trip> create(Trip trip);
    public ResponseEntity<Trip> updateById(Long id, Trip trip);
    public ResponseEntity<HttpStatus> deleteById(Long id);
}
