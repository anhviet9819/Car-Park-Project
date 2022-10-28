package com.example.carparkproject.repository;

import com.example.carparkproject.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "select * from ticket t where t.licensePlate = :licensePlate", nativeQuery = true)
    List<Ticket> findByLicensePlate(@Param("licensePlate")String licensePlate);

    @Query(value = "select * from ticket t where t.tripId = :tripId", nativeQuery = true)
    List<Ticket> findByTripId(@Param("tripId")Long tripId);

//    @Query(value = "select * from ticket t where bookingTime >= :startTime and bookingTime <= :endTime", nativeQuery = true)
    List<Ticket> findByBookingTimeBetween(Time startTime, Time endTime);

    @Query(value = "select count(*) from ticket group by(tripId) having tripId = :tripId", nativeQuery = true)
    int findCountTicketByTripId(@Param("tripId")Long id);
}
