package com.example.carparkproject.controller;

import com.example.carparkproject.entity.Bookingoffice;
import com.example.carparkproject.service.IBookingofficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookingoffice")
public class BookingofficeController {
    @Autowired
    private IBookingofficeService bookingofficeService;

    @GetMapping("/search")
    public ResponseEntity<List<Bookingoffice>> getAllBookingoffice(){
        return bookingofficeService.getAll();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Bookingoffice> getBookingofficeById(@PathVariable("id")Long id){
        return bookingofficeService.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Bookingoffice> createBookingoffice(@RequestBody @Validated Bookingoffice bookingoffice,
                                                   BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        return bookingofficeService.create(bookingoffice);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Bookingoffice> updateBookingofficeById(@RequestBody Bookingoffice bookingoffice,
                                                       @PathVariable("id")Long id) throws BindException {
//        if(bindingResult.hasErrors()){
//            throw new BindException(bindingResult);
//        }
        return bookingofficeService.updateById(id, bookingoffice);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBookingofficeById(@PathVariable("id")Long id){
        return bookingofficeService.deleteById(id);
    }
}
