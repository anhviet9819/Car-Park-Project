package com.example.carparkproject.controller;

import com.example.carparkproject.entity.Car;
import com.example.carparkproject.entity.Parkinglot;
import com.example.carparkproject.service.ICarService;
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
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    private ICarService carService;

    @GetMapping("/search")
    public ResponseEntity<List<Car>> getAllCar(){
        return carService.getAll();
    }

    @GetMapping("/details/parkinglot/{parkinglotid}")
    public ResponseEntity<List<Car>> getCarByParkId(@PathVariable("parkinglotid") Long parkId){
        return carService.getByParkId(parkId);
    }

    @GetMapping("/details/carcolor/{carcolor}")
    public ResponseEntity<List<Car>> getCarByCarColor(@PathVariable("carcolor") String carColor){
        return carService.getByCarColor(carColor);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id")String id){
        return carService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Car> createCar(@RequestBody @Validated Car car,
                                                       BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        return carService.create(car);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<Car> updateCarById(@RequestBody @Validated Car car,
//                                                           BindingResult bindingResult, @PathVariable("id")String id) throws BindException {
//        if(bindingResult.hasErrors()){
//            throw new BindException(bindingResult);
//        }
//        return carService.updateById(id, car);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Car> updateCarById(@RequestBody Car car,
                                             @PathVariable("id")String id){
        return carService.updateById(id, car);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCarById(@PathVariable("id")String id){
        return carService.deleteById(id);
    }
}
