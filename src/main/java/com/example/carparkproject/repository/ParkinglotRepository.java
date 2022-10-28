package com.example.carparkproject.repository;

import com.example.carparkproject.entity.Parkinglot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkinglotRepository extends JpaRepository<Parkinglot, Long> {
}
