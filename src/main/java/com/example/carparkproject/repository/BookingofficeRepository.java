package com.example.carparkproject.repository;

import com.example.carparkproject.entity.Bookingoffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingofficeRepository extends JpaRepository<Bookingoffice, Long> {
}
