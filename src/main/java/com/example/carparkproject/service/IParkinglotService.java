package com.example.carparkproject.service;

import com.example.carparkproject.entity.Parkinglot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IParkinglotService {
    public ResponseEntity<List<Parkinglot>> getAll();
    public ResponseEntity<Parkinglot> getById(Long id);
    public ResponseEntity<Parkinglot> updateById(Long id, Parkinglot parkinglot);
    public ResponseEntity<Parkinglot> create(Parkinglot parkinglot);
    public ResponseEntity<HttpStatus> deleteById(Long id);
}
