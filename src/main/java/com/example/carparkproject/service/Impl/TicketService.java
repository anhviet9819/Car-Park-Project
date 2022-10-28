package com.example.carparkproject.service.Impl;

import com.example.carparkproject.entity.Car;
import com.example.carparkproject.entity.Ticket;
import com.example.carparkproject.entity.Trip;
import com.example.carparkproject.exception.DuplicateIdException;
import com.example.carparkproject.exception.FullSlotException;
import com.example.carparkproject.exception.ResourceNotFoundException;
import com.example.carparkproject.repository.CarRepository;
import com.example.carparkproject.repository.TicketRepository;
import com.example.carparkproject.repository.TripRepository;
import com.example.carparkproject.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Instant;
import java.util.List;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private CarRepository carRepository;

    @Override
    public ResponseEntity<List<Ticket>> getAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ticket>> getByLicensePlate(String licensePlate) {
        Car car = carRepository.findById(licensePlate).orElseThrow(
                () -> new ResourceNotFoundException("Not found car with license plate: "+licensePlate)
        );
        List<Ticket> tickets = ticketRepository.findByLicensePlate(licensePlate);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ticket>> getByTripId(Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(
                () -> new ResourceNotFoundException("Not found trip with id: "+tripId)
        );
        List<Ticket> tickets = ticketRepository.findByTripId(tripId);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ticket>> getByBookingTime(Time startTime, Time endTime) {
        List<Ticket> tickets = ticketRepository.findByBookingTimeBetween(startTime, endTime);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Ticket> getById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found ticket with id: " + id));
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Ticket> create(Ticket ticket) {
//        Ticket ticket1 = ticketRepository.findById(ticket.getTicketId()).orElse(null);
//        if(ticket1 != null) throw new DuplicateIdException("duplicate ticket id: "+ ticket1.getTicketId());
        Trip trip = tripRepository.findById(ticket.getTrip().getTripId()).orElseThrow(
                () -> new ResourceNotFoundException("Not found trip with id: " + ticket.getTrip().getTripId())
        );    //get trip where ticket create
        Car car = carRepository.findById(ticket.getCar().getLicensePlate()).orElseThrow(
                () -> new ResourceNotFoundException("Not found car with id: " + ticket.getCar().getLicensePlate())
        );

        //Add ticket
        if (trip.getBookedTicketNumber() == trip.getMaximumOnlineTicketNumber()) {        //Full
            throw new FullSlotException("Trip you 've booked is full! Try with another trip!"); // truyen vao tripId
        }

        //Add when trip not full
        ticket.setBookingTime(new Time(System.currentTimeMillis()));
        Ticket ticket1 = ticketRepository.save(ticket);
        //Update bookedTicketNumber
        trip.setBookedTicketNumber(ticketRepository.findCountTicketByTripId(ticket1.getTrip().getTripId()));    // update new bookedTicketNumber
        tripRepository.save(trip);
        return new ResponseEntity<>(ticket1, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Ticket> updateById(Long id, Ticket ticket) {
        Ticket ticket1 = ticketRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found ticket with id: " + id)
        );
        if(ticket.getBookingTime() != null){
            ticket1.setBookingTime(ticket.getBookingTime());
        }
        if(ticket.getCustomerName() != null){
            ticket1.setCustomerName(ticket.getCustomerName());
        }

        if(ticket.getTrip() != null){
            Trip tripTransferTo = tripRepository.findById(ticket.getTrip().getTripId()).orElseThrow(
                    () -> new ResourceNotFoundException("Not found trip with id: " + ticket.getTrip().getTripId())
            );
            if (tripTransferTo.getBookedTicketNumber() == tripTransferTo.getMaximumOnlineTicketNumber()) {        //Full
                throw new FullSlotException("Trip you 've booked is full! Try with another trip!");
            }
            //Update bookedTicketNumber
            tripTransferTo.setBookedTicketNumber(tripTransferTo.getBookedTicketNumber() + 1);    // update new bookedTicketNumber for trip transfer to
            Trip tripTransferFrom = tripRepository.findById(ticket1.getTrip().getTripId()).orElse(null); // thua vi ticket luon co trip
            if(tripTransferFrom != null){
                tripTransferFrom.setBookedTicketNumber(tripTransferFrom.getBookedTicketNumber() - 1);    // update new bookedTicketNumber for trip transfer from
                tripRepository.save(tripTransferFrom);
            }
            tripRepository.save(tripTransferTo);
            ticket1.setTrip(tripTransferTo);  // co the thừa vi trip persist thi ticket1 se cap nhat => khong can set, chi can save cac thanh phan khac ben duoi
        }

        if(ticket.getCar() != null){
            Car car = carRepository.findById(ticket.getCar().getLicensePlate()).orElseThrow(
                    () -> new ResourceNotFoundException("Not found car with license plate: " + ticket.getCar().getLicensePlate())
            );
            ticket1.setCar(car);  // co the thừa vi trip persist thi ticket1 se cap nhat => khong can set, chi can save cac thanh phan khac ben duoi
        }
        return new ResponseEntity<>(ticketRepository.save(ticket1), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found ticket with id = " + id));
        Long tripId = ticket.getTrip().getTripId();
        int bookedTicketNumber = ticketRepository.findCountTicketByTripId(tripId);
        ticketRepository.delete(ticket);

        //Update bookedTicketNumber
        Trip trip = tripRepository.findById(tripId).orElse(null);    //find trip where ticket create
        trip.setBookedTicketNumber(bookedTicketNumber - 1);    // update new bookedTicketNumber
        tripRepository.save(trip);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
