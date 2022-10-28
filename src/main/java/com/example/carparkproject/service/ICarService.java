package com.example.carparkproject.service;

import com.example.carparkproject.entity.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICarService {
    public ResponseEntity<List<Car>> getAll();
    public ResponseEntity<List<Car>> getByParkId(Long parkId);
    public ResponseEntity<List<Car>> getByCarColor(String carColor);
    public ResponseEntity<Car> getById(String id);
    public ResponseEntity<Car> create(Car car);
    public ResponseEntity<Car> updateById(String id, Car car);
    public ResponseEntity<HttpStatus> deleteById(String id);
}
