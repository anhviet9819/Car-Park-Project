package com.example.carparkproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "parkinglot")
public class Parkinglot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parkId")
    private Long parkId;

    @Column(name = "parkArea")
    private Long parkArea;
    @NotNull
    @Size(min = 3, max = 50, message = "park name has min 3 char and max 50 char!")
    @Column(name = "parkName")
    private String parkName;
    @NotNull
    @Size(min = 3, max = 11, message = "park place has min 3 char and max 11 char!")
    @Column(name = "parkPlace")
    private String parkPlace;
    @Column(name = "parkPrice")
    private Long parkPrice;

    @Column(name = "parkStatus")
    @Size(min = 3, max = 45, message = "park status has min 3 char and max 45 char!")
    @Pattern(regexp = "Blank|Full", message = "Park status must be full or blank!")
    private String parkStatus;

    public Parkinglot() {
    }

    @OneToMany(mappedBy = "parkinglot", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //update 2:06
//    @OneToMany(mappedBy = "parkinglot", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Car> cars;

    public Parkinglot(Long parkArea, String parkName, String parkPlace, Long parkPrice, String parkStatus, List<Car> car) {
        this.parkArea = parkArea;
        this.parkName = parkName;
        this.parkPlace = parkPlace;
        this.parkPrice = parkPrice;
        this.parkStatus = parkStatus;
        this.cars = car;
    }

    public Long getParkId() {
        return parkId;
    }

    public void setParkId(Long parkId) {
        this.parkId = parkId;
    }

    public Long getParkArea() {
        return parkArea;
    }

    public void setParkArea(Long parkArea) {
        this.parkArea = parkArea;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getParkPlace() {
        return parkPlace;
    }

    public void setParkPlace(String parkPlace) {
        this.parkPlace = parkPlace;
    }

    public Long getParkPrice() {
        return parkPrice;
    }

    public void setParkPrice(Long parkPrice) {
        this.parkPrice = parkPrice;
    }

    public String getParkStatus() {
        return parkStatus;
    }

    public void setParkStatus(String parkStatus) {
        this.parkStatus = parkStatus;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
