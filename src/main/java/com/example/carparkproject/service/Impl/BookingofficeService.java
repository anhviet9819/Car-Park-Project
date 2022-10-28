package com.example.carparkproject.service.Impl;

import com.example.carparkproject.entity.Bookingoffice;
import com.example.carparkproject.entity.Car;
import com.example.carparkproject.entity.Trip;
import com.example.carparkproject.exception.ResourceNotFoundException;
import com.example.carparkproject.repository.BookingofficeRepository;
import com.example.carparkproject.repository.TripRepository;
import com.example.carparkproject.service.IBookingofficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingofficeService implements IBookingofficeService {
    @Autowired
    private BookingofficeRepository bookingofficeRepository;
    @Autowired
    private TripRepository tripRepository;

    @Override
    public ResponseEntity<List<Bookingoffice>> getAll() {
        List<Bookingoffice> bookingoffices = bookingofficeRepository.findAll();
        if(bookingoffices.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookingoffices, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Bookingoffice> getById(Long id) {
        Bookingoffice bookingoffice = bookingofficeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found booking office lot with id: "+ id));
        return new ResponseEntity<>(bookingoffice, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Bookingoffice> updateById(Long id, Bookingoffice bookingoffice) {
        Bookingoffice bookingoffice1 = bookingofficeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found booking office with id = " + id));

        if(bookingoffice.getOfficeName() != null){
            bookingoffice1.setOfficeName(bookingoffice.getOfficeName());
        }
        if(bookingoffice.getOfficePhone() != null){
            bookingoffice1.setOfficePhone(bookingoffice.getOfficePhone());
        }
        if(bookingoffice.getEndContractDeadline() != null){
            bookingoffice1.setEndContractDeadline(bookingoffice.getEndContractDeadline());
        }
        if(bookingoffice.getStartContractDeadline() != null){
            bookingoffice1.setStartContractDeadline(bookingoffice.getStartContractDeadline());
        }
        if(bookingoffice.getOfficePlace() != null){
            bookingoffice1.setOfficePlace(bookingoffice.getOfficePlace());
        }
        if(bookingoffice.getOfficePrice() != null){
            bookingoffice1.setOfficePrice(bookingoffice.getOfficePrice());
        }
        if(bookingoffice.getTrip() != null){
            Trip trip = tripRepository.findById(bookingoffice.getTrip().getTripId()).orElseThrow(
                    () -> new ResourceNotFoundException("Not found trip with id: "+ bookingoffice.getTrip().getTripId())
            );
            bookingoffice1.setTrip(trip);
        }
        return new ResponseEntity<>(bookingofficeRepository.save(bookingoffice1), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Bookingoffice> create(Bookingoffice bookingoffice) {
        Bookingoffice bookingoffice1 = bookingofficeRepository.save(bookingoffice);
        return new ResponseEntity<>(bookingoffice1, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        Bookingoffice bookingoffice = bookingofficeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found booking office with id = " + id));

        bookingofficeRepository.delete(bookingoffice);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
