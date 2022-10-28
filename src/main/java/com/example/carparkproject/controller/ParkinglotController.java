package com.example.carparkproject.controller;

import com.example.carparkproject.entity.Parkinglot;
import com.example.carparkproject.service.IParkinglotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkinglot")
public class ParkinglotController {
    @Autowired
    private IParkinglotService parkinglotService;

    @GetMapping("/search")
    public ResponseEntity<List<Parkinglot>> getAllParkinglot(){
        return parkinglotService.getAll();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Parkinglot> getParkinglotById(@PathVariable("id")Long id){
        return parkinglotService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Parkinglot> createParkinglot(@RequestBody @Validated Parkinglot parkinglot
                                                      ) throws BindException {
//        if(bindingResult.hasErrors()){
//            throw new BindException(bindingResult);
//        }
        return parkinglotService.create(parkinglot);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<Parkinglot> updateParkinglotById(@RequestBody @Validated Parkinglot parkinglot,
//                                                       BindingResult bindingResult, @PathVariable("id")Long id) throws BindException {
//        if(bindingResult.hasErrors()){
//            throw new BindException(bindingResult);
//        }
//        return parkinglotService.updateById(id, parkinglot);
//    }

    @PutMapping("/update/{id}") //Dung patch
    public ResponseEntity<Parkinglot> updateParkinglotById(@RequestBody Parkinglot parkinglot,
                                                           @PathVariable("id")Long id){
        return parkinglotService.updateById(id, parkinglot);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteParkinglotById(@PathVariable("id")Long id){
        return parkinglotService.deleteById(id);
    }
}
