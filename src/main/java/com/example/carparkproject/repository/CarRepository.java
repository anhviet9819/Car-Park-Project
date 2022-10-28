package com.example.carparkproject.repository;

import com.example.carparkproject.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    @Query(value = "select * from car c where c.parkId = :parkId", nativeQuery = true)
    List<Car> findByParkId(@Param("parkId")Long parkId);
    List<Car> findByCarColor(String color);
}
