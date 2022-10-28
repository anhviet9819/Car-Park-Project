package com.example.carparkproject.service.Impl;

import com.example.carparkproject.entity.Bookingoffice;
import com.example.carparkproject.entity.Car;
import com.example.carparkproject.entity.Trip;
import com.example.carparkproject.exception.ResourceNotFoundException;
import com.example.carparkproject.repository.BookingofficeRepository;
import com.example.carparkproject.repository.TripRepository;
import com.example.carparkproject.service.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService implements ITripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private BookingofficeRepository bookingofficeRepository;

    @Override
    public ResponseEntity<List<Trip>> getAll() {
        List<Trip> trips = tripRepository.findAll();
        if(trips.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Trip>> getByDestinationAndCarType(String destination, String carType) {
        List<Trip> trips = tripRepository.findByDestinationAndCarType(destination, carType);
        if(trips.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Trip> getById(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found trip with id: "+ id));
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Trip> create(Trip trip) {
        if(trip.getBookedTicketNumber() == null){
            trip.setBookedTicketNumber(0);
        }
        Trip trip1 = tripRepository.save(trip);
        return new ResponseEntity<>(trip1, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Trip> updateById(Long id, Trip trip) {
        Trip trip1 = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found trip with id = " + id));

        if(trip.getBookedTicketNumber() != null){
            trip1.setBookedTicketNumber(trip.getBookedTicketNumber());
        }
        if(trip.getCarType() != null){
            trip1.setCarType(trip.getCarType());
        }
        if(trip.getDestination() != null){
            trip1.setDestination(trip.getDestination());
        }
        if(trip.getDriver() != null){
            trip1.setDriver(trip.getDriver());
        }
        if(trip.getDepartureDate() != null){
            trip1.setDepartureDate(trip.getDepartureDate());
        }
        if(trip.getDepartureTime() != null){
            trip1.setDepartureTime(trip.getDepartureTime());
        }
        if(trip.getMaximumOnlineTicketNumber() != null){
            trip1.setMaximumOnlineTicketNumber(trip.getMaximumOnlineTicketNumber());
        }
        return new ResponseEntity<>(tripRepository.save(trip1), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found trip with id = " + id));
        if(!trip.getBookingoffices().isEmpty()){
            for(Bookingoffice bookingoffice : trip.getBookingoffices()){
                bookingoffice.setTrip(null);
                bookingofficeRepository.save(bookingoffice);
            }
        }

        tripRepository.delete(trip);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
