package com.example.carparkproject.service.Impl;

import com.example.carparkproject.entity.Car;
import com.example.carparkproject.entity.Parkinglot;
import com.example.carparkproject.exception.DuplicateIdException;
import com.example.carparkproject.exception.FullSlotException;
import com.example.carparkproject.exception.ResourceNotFoundException;
import com.example.carparkproject.repository.CarRepository;
import com.example.carparkproject.repository.ParkinglotRepository;
import com.example.carparkproject.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarService implements ICarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ParkinglotRepository parkinglotRepository;

    @Override
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carRepository.findAll();
        if(cars.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Car>> getByParkId(Long parkId) {
        List<Car> cars = carRepository.findByParkId(parkId);
        if(cars.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Car>> getByCarColor(String carColor) {
        List<Car> cars = carRepository.findByCarColor(carColor);
        if(cars.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Car> getById(String id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found car with id: "+ id));
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Car> create(Car car) {
        if(car.getParkinglot() != null){
            Parkinglot parkinglot = parkinglotRepository.findById(car.getParkinglot().getParkId()).orElseThrow(
                    () -> new ResourceNotFoundException("Not found parkinglot with id: "+ car.getParkinglot().getParkId())
            );

            if(!parkinglot.getParkStatus().equals("Blank")){
                throw new FullSlotException("The parking lot is full! Try with another parking lot!");
            }
            Car car1 = carRepository.findById(car.getLicensePlate()).orElse(null);
            if(car1 != null) throw new DuplicateIdException("duplicate license plate: "+ car.getLicensePlate());
            return new ResponseEntity<>(carRepository.save(car), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(carRepository.save(car), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Car> updateById(String id, Car car) {
        Car car1 = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found car with id = " + id));

        if(car.getCarColor() != null){
            car1.setCarColor(car.getCarColor());
        }
        if(car.getCarType() != null){
            car1.setCarType(car.getCarType());
        }
        if(car.getCompany() != null){
            car1.setCompany(car.getCompany());
        }
        if(car.getParkinglot().getParkId() != null){
            Parkinglot parkinglot = parkinglotRepository.findById(car.getParkinglot().getParkId()).orElseThrow(
                    () -> new ResourceNotFoundException("Not found parkinglot with id: "+ car.getParkinglot().getParkId())
            );
            if(parkinglot.getParkStatus().equals("Full")){      //update 2:40: set full parking lot
                throw new FullSlotException("The parking lot is full! Try with another parking lot!");
            }
            car1.setParkinglot(parkinglot);
        }
        return new ResponseEntity<>(carRepository.save(car1), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(String id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found car with id = " + id));

        carRepository.delete(car);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
