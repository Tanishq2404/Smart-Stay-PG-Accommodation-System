package com.smartstay.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @NotBlank(message = "Room Number is required")
    @Column(nullable = false, length = 20)
    private String roomNumber;

    @NotBlank(message = "Room Type is required")
    @Column(nullable = false, length = 30)
    private String roomType;

    @NotNull(message = "Rent is required")
    @Positive(message = "Rent must be greater than 0")
    @Column(nullable = false)
    private Double rent;

    @NotNull(message = "Total Beds is required")
    @Positive(message = "Total Beds must be greater than 0")
    @Column(nullable = false)
    private Integer totalBeds;

    @NotNull(message = "Available Beds is required")
    @Positive(message = "Available Beds must be greater than 0")
    @Column(nullable = false)
    private Integer availableBeds;

    @Column(nullable = false)
    private Boolean acAvailable;

    @Column(nullable = false)
    private Boolean attachedBathroom;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pg_id", nullable = false)
    private Pg pg;

    // Default Constructor
    public Room() {
    }

    // Parameterized Constructor
    public Room(Long roomId, Pg pg, String roomNumber, String roomType,
                Double rent, Integer totalBeds, Integer availableBeds,
                Boolean acAvailable, Boolean attachedBathroom) {
        this.roomId = roomId;
        this.pg = pg;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.rent = rent;
        this.totalBeds = totalBeds;
        this.availableBeds = availableBeds;
        this.acAvailable = acAvailable;
        this.attachedBathroom = attachedBathroom;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Integer getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(Integer totalBeds) {
        this.totalBeds = totalBeds;
    }

    public Integer getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(Integer availableBeds) {
        this.availableBeds = availableBeds;
    }

    public Boolean getAcAvailable() {
        return acAvailable;
    }

    public void setAcAvailable(Boolean acAvailable) {
        this.acAvailable = acAvailable;
    }

    public Boolean getAttachedBathroom() {
        return attachedBathroom;
    }

    public void setAttachedBathroom(Boolean attachedBathroom) {
        this.attachedBathroom = attachedBathroom;
    }

    public Pg getPg() {
        return pg;
    }

    public void setPg(Pg pg) {
        this.pg = pg;
    }
}