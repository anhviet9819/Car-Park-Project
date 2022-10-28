package com.example.carparkproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "bookingoffice")
public class Bookingoffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "officeId")
    private Long officeId;

    @Column(name = "endContractDeadline")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endContractDeadline;

    @NotNull
    @Column(name = "officeName")
    @Size(min = 2, max = 50, message = "Office name has min 2 char and max 50 char")
    private String officeName;

    @NotNull
    @Column(name = "officePhone")
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Phone number must be 10 digit and starts with 84 or 03, 05, 07, 08, 09!")
    private String officePhone;

    @Column(name = "officePlace")
    @Size(min = 2, max = 50, message = "Office place has min 2 char and max 50 char")
    private String officePlace;

    @NotNull
    @Column(name = "officePrice")
    private Long officePrice;

    @Column(name = "startContractDeadline")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startContractDeadline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tripId", referencedColumnName = "tripId")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Trip trip;

    public Bookingoffice(Date endContractDeadline, String officeName, String officePhone, String officePlace, Long officePrice, Date startContractDeadline, Trip trip) {
        this.endContractDeadline = endContractDeadline;
        this.officeName = officeName;
        this.officePhone = officePhone;
        this.officePlace = officePlace;
        this.officePrice = officePrice;
        this.startContractDeadline = startContractDeadline;
        this.trip = trip;
    }

    public Bookingoffice() {

    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Date getEndContractDeadline() {
        return endContractDeadline;
    }

    public void setEndContractDeadline(Date endContractDeadline) {
        this.endContractDeadline = endContractDeadline;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getOfficePlace() {
        return officePlace;
    }

    public void setOfficePlace(String officePlace) {
        this.officePlace = officePlace;
    }

    public Long getOfficePrice() {
        return officePrice;
    }

    public void setOfficePrice(Long officePrice) {
        this.officePrice = officePrice;
    }

    public Date getStartContractDeadline() {
        return startContractDeadline;
    }

    public void setStartContractDeadline(Date startContractDeadline) {
        this.startContractDeadline = startContractDeadline;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
