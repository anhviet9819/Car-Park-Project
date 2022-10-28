package com.example.carparkproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;

@Entity
@Table(name = "ticket")
public class Ticket {
//    @EmbeddedId
//    @JsonIgnore
//    private TicketId ticketEmbeddedId;
//
//    @ManyToOne
//    @MapsId("licensePlate")
//    @JoinColumn(name = "licensePlate")
////    @JsonIgnore
////    @OnDelete(action = OnDeleteAction.CASCADE)
//    Car car;
//
//    @ManyToOne
//    @MapsId("tripId")
//    @JoinColumn(name = "tripId")
////    @JsonIgnore
////    @OnDelete(action = OnDeleteAction.CASCADE)
//    Trip trip;
//
//    @Column(name = "bookingTime")
//    private Time bookingTime;
//
//    @NotNull
//    @Column(name = "customerName")
//    private String customerName;
//
//    public TicketId getTicketEmbeddedId() {
//        return ticketEmbeddedId;
//    }
//
//    public void setTicketEmbeddedId(TicketId ticketEmbeddedId) {
//        this.ticketEmbeddedId = ticketEmbeddedId;
//    }
//
//    public Car getCar() {
//        return car;
//    }
//
//    public void setCar(Car car) {
//        this.car = car;
//    }
//
//    public Trip getTrip() {
//        return trip;
//    }
//
//    public void setTrip(Trip trip) {
//        this.trip = trip;
//    }
//
//    public Time getBookingTime() {
//        return bookingTime;
//    }
//
//    public void setBookingTime(Time bookingTime) {
//        this.bookingTime = bookingTime;
//    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId")
    private Long ticketId;

    @Column(name = "bookingTime")
    @DateTimeFormat(pattern="hh:mm:ss")
    private Time bookingTime;

    @NotNull
    @Column(name = "customerName")
    @Size(min = 3, max = 30, message = "Customer name between 3 char and 30 char!")
    private String customerName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "licensePlate", referencedColumnName = "licensePlate")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    @NotNull
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tripId", referencedColumnName = "tripId")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    @NotNull
    private Trip trip;

    public Ticket(Time bookingTime, String customerName, Car car, Trip trip) {
        this.bookingTime = bookingTime;
        this.customerName = customerName;
        this.car = car;
        this.trip = trip;
    }

    public Ticket() {

    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Time getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Time bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
