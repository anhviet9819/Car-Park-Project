package com.example.carparkproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "licensePlate", unique = true)
    @Pattern(regexp = "[0-9]{2}-[A-Za-z][0-9]-[0-9]{4,5}", message = "VietNam licenseplate invalid!")
    private String licensePlate;

    @NotNull
    @Column(name = "carColor")
    @Size(min = 3, max = 11, message = "car color has min 3 char and max 11 char!")

    private String carColor;

    @Column(name = "carType")
    @Size(min = 3, max = 50, message = "car type has min 3 char and max 30 char!")
    private String carType;

    @Column(name = "company")
    @Size(min = 3, max = 50, message = "company has min 3 char and max 30 char!")
    private String company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parkId", referencedColumnName = "parkId")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    @NotNull
    private Parkinglot parkinglot;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    public Car(String carColor, String carType, String company, Parkinglot parkinglot, List<Ticket> tickets) {
        this.carColor = carColor;
        this.carType = carType;
        this.company = company;
        this.parkinglot = parkinglot;
        this.tickets = tickets;
    }

    public Car() {

    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Parkinglot getParkinglot() {
        return parkinglot;
    }

    public void setParkinglot(Parkinglot parkinglot) {
        this.parkinglot = parkinglot;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
