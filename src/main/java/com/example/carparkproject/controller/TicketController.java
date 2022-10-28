package com.example.carparkproject.controller;

import com.example.carparkproject.entity.Ticket;
import com.example.carparkproject.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private ITicketService ticketService;

    @GetMapping("/search")
    public ResponseEntity<List<Ticket>> getAllTicket(){
        return ticketService.getAll();
    }

    @GetMapping("/details/licenseplate/{licenseplate}")
    public ResponseEntity<List<Ticket>> getTicketByLicensePlate(@PathVariable("licenseplate")String licensePlate){
        return ticketService.getByLicensePlate(licensePlate);
    }

    @GetMapping("/details/trip/{tripid}")
    public ResponseEntity<List<Ticket>> getTicketByTripId(@PathVariable("tripid")Long tripId){
        return ticketService.getByTripId(tripId);
    }

    @GetMapping("/details/bookingtime")
    public ResponseEntity<List<Ticket>> getTicketByBookingTime(@RequestParam Time starttime, @RequestParam Time endtime){
        return ticketService.getByBookingTime(starttime, endtime);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id")Long id){
        return ticketService.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody @Validated Ticket ticket,
                                               BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        return ticketService.create(ticket);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Ticket> updateTicketById(@PathVariable("id")Long id, @RequestBody Ticket ticket){
        return ticketService.updateById(id, ticket);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTicketById(@PathVariable("id")Long id){
        return ticketService.deleteById(id);
    }
}
