package com.example.carparkproject.service;

import com.example.carparkproject.entity.Car;
import com.example.carparkproject.entity.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Time;
import java.util.List;

public interface ITicketService {
    public ResponseEntity<List<Ticket>> getAll();
    public ResponseEntity<List<Ticket>> getByLicensePlate(String licensePlate);
    public ResponseEntity<List<Ticket>> getByTripId(Long tripId);
    public ResponseEntity<List<Ticket>> getByBookingTime(Time startTime, Time endTime);
    public ResponseEntity<Ticket> getById(Long id);
    public ResponseEntity<Ticket> create(Ticket ticket);
    public ResponseEntity<Ticket> updateById(Long id, Ticket ticket);
    public ResponseEntity<HttpStatus> deleteById(Long id);
}
