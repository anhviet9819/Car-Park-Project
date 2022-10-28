package com.example.carparkproject.service;

import com.example.carparkproject.entity.Bookingoffice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBookingofficeService {
    public ResponseEntity<List<Bookingoffice>> getAll();
    public ResponseEntity<Bookingoffice> getById(Long id);
    public ResponseEntity<Bookingoffice> updateById(Long id, Bookingoffice bookingoffice);
    public ResponseEntity<Bookingoffice> create(Bookingoffice bookingoffice);
    public ResponseEntity<HttpStatus> deleteById(Long id);
}
