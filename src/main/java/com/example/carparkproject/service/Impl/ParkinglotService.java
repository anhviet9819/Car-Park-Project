package com.example.carparkproject.service.Impl;

import com.example.carparkproject.entity.Car;
import com.example.carparkproject.entity.Parkinglot;
import com.example.carparkproject.exception.ResourceNotFoundException;
import com.example.carparkproject.repository.CarRepository;
import com.example.carparkproject.repository.ParkinglotRepository;
import com.example.carparkproject.service.IParkinglotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkinglotService implements IParkinglotService {

    @Autowired
    private ParkinglotRepository parkinglotRepository;
    @Autowired
    private CarRepository carRepository;

    @Override
    public ResponseEntity<List<Parkinglot>> getAll() {
        List<Parkinglot> parkinglots = parkinglotRepository.findAll();
        if (parkinglots.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(parkinglots, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Parkinglot> getById(Long id) {
        Parkinglot parkinglot = parkinglotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found parking lot with id: " + id));
        return new ResponseEntity<>(parkinglot, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Parkinglot> updateById(Long id, Parkinglot parkinglot) {
        Parkinglot parkinglot1 = parkinglotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found parking lot with id = " + id));

        if (parkinglot.getParkName() != null) {
            parkinglot1.setParkName(parkinglot.getParkName());
        }
        if (parkinglot.getParkArea() != null) {
            parkinglot1.setParkArea(parkinglot.getParkArea());
        }
        if (parkinglot.getParkPlace() != null) {
            parkinglot1.setParkPlace(parkinglot.getParkPlace());
        }
        if (parkinglot.getParkPrice() != null) {
            parkinglot1.setParkPrice(parkinglot.getParkPrice());
        }
        if (parkinglot.getParkStatus() != null) {
            parkinglot1.setParkStatus(parkinglot.getParkStatus());
        }

        return new ResponseEntity<>(parkinglotRepository.save(parkinglot1), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Parkinglot> create(Parkinglot parkinglot) {
        if (parkinglot.getParkStatus() == null) {
            parkinglot.setParkStatus("Blank");
        }
        Parkinglot parkinglot1 = parkinglotRepository.save(parkinglot);
        return new ResponseEntity<>(parkinglot1, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        Parkinglot parkinglot = parkinglotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found parking lot with id = " + id));
        //Check if cars in parkinglot
        if (!parkinglot.getCars().isEmpty()) {
            for (Car car : parkinglot.getCars()) {
                car.setParkinglot(null);
                carRepository.save(car);
            }
        }
        parkinglotRepository.delete(parkinglot);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
