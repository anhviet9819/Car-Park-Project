package com.example.carparkproject.controller;

import com.example.carparkproject.entity.Car;
import com.example.carparkproject.entity.Trip;
import com.example.carparkproject.service.ICarService;
import com.example.carparkproject.service.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    @Autowired
    private ITripService tripService;

    @GetMapping("/search")
    public ResponseEntity<List<Trip>> getAllTrip(){
        return tripService.getAll();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable("id")Long id){
        return tripService.getById(id);
    }

    @GetMapping("/details/destinationandcartype")
    public ResponseEntity<List<Trip>> getTripById(@RequestParam String destination, @RequestParam String cartype){
        return tripService.getByDestinationAndCarType(destination, cartype);
    }

    @PostMapping("/add")
    public ResponseEntity<Trip> createTrip(@RequestBody @Validated Trip trip,
                                         BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        return tripService.create(trip);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Trip> updateTripById(@RequestBody @Validated Trip trip,
                                             BindingResult bindingResult, @PathVariable("id")Long id) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        return tripService.updateById(id, trip);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTripById(@PathVariable("id")Long id){
        return tripService.deleteById(id);
    }
}
