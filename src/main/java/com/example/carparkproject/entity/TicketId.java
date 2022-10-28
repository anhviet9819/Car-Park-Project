package com.example.carparkproject.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TicketId implements Serializable {
    private static final long serialVersionUID = -1541344455730857340L;
    @Column(name = "licensePlate")
    private String licensePlate;
    @Column(name = "tripId")
    private Long tripId;

    public TicketId(String licensePlate, Long tripId) {
        this.licensePlate = licensePlate;
        this.tripId = tripId;
    }

    public TicketId() {

    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}
