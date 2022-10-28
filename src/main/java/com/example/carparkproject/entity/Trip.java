package com.example.carparkproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tripId")
    private Long tripId;

    @Column(name = "bookedTicketNumber")
//    @Size(min = 0)
    private Integer bookedTicketNumber;

    @NotNull
    @Column(name = "carType")
    @Size(min = 3, max = 30, message = "car type has min 3 char and max 30 char!")
    private String carType;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "departureDate")
    private Date departureDate;

    @NotNull
    @DateTimeFormat(pattern="hh:mm:ss")
    @Column(name = "departureTime")
    private Time departureTime;

    @NotNull
    @Column(name = "destination")
    @Size(min = 3, max = 50, message = "destination has min 3 char and max 50 char!")
    private String destination;

    @NotNull
    @Column(name = "driver")
    @Size(min = 3, max = 30, message = "driver has min 3 char and max 30 char!")
    private String driver;

    @NotNull
    @Column(name = "maximumOnlineTicketNumber")
    private Integer maximumOnlineTicketNumber;

//    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "trip", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Bookingoffice> bookingoffices;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    public Trip(Integer bookedTicketNumber, String carType, Date departureDate, Time departureTime, String destination, String driver, Integer maximumOnlineTicketNumber, List<Bookingoffice> bookingoffices, List<Ticket> tickets) {
        this.bookedTicketNumber = bookedTicketNumber;
        this.carType = carType;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.driver = driver;
        this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
        this.bookingoffices = bookingoffices;
        this.tickets = tickets;
    }

    public Trip(Integer bookedTicketNumber, String carType, Date departureDate, Time departureTime, String destination, String driver, Integer maximumOnlineTicketNumber) {
        this.bookedTicketNumber = bookedTicketNumber;
        this.carType = carType;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.driver = driver;
        this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
    }

    public Trip() {

    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Integer getBookedTicketNumber() {
        return bookedTicketNumber;
    }

    public void setBookedTicketNumber(Integer bookedTicketNumber) {
        this.bookedTicketNumber = bookedTicketNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Integer getMaximumOnlineTicketNumber() {
        return maximumOnlineTicketNumber;
    }

    public void setMaximumOnlineTicketNumber(Integer maximumOnlineTicketNumber) {
        this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
    }

    public List<Bookingoffice> getBookingoffices() {
        return bookingoffices;
    }

    public void setBookingoffices(List<Bookingoffice> bookingoffices) {
        this.bookingoffices = bookingoffices;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
