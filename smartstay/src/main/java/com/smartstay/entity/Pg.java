package com.smartstay.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "pgs")
public class Pg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pgId;

    @NotBlank(message = "PG Name is required")
    @Column(nullable = false, length = 100)
    private String pgName;

    @Column(length = 1000)
    private String description;

    @NotBlank(message = "Address is required")
    @Column(nullable = false, length = 300)
    private String address;

    @NotBlank(message = "City is required")
    @Column(nullable = false, length = 50)
    private String city;

    @NotBlank(message = "State is required")
    @Column(nullable = false, length = 50)
    private String state;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    @Column(nullable = false, length = 6)
    private String pincode;

    @NotNull(message = "Starting Rent is required")
    @Positive(message = "Rent must be greater than zero")
    @Column(nullable = false)
    private Double rentStarting;

    @NotNull(message = "Available Beds is required")
    @PositiveOrZero(message = "Available Beds cannot be negative")
    @Column(nullable = false)
    private Integer availableBeds;

    @Column(nullable = false)
    private Boolean wifiAvailable = false;

    @Column(nullable = false)
    private Boolean foodAvailable = false;

    @Column(nullable = false)
    private Boolean parkingAvailable = false;

    @Column(nullable = false)
    private Double rating = 0.0;

    @Column(length = 500)
    private String imageUrl;

    @JsonIgnoreProperties({"pgs"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @JsonManagedReference
    @OneToMany(mappedBy = "pg", cascade = CascadeType.ALL)
    private List<Room> rooms;

    // Default Constructor
    public Pg() {
    }

    // Parameterized Constructor
    public Pg(Long pgId, Owner owner, String pgName, String description,
              String address, String city, String state, String pincode,
              Double rentStarting, Integer availableBeds,
              Boolean wifiAvailable, Boolean foodAvailable,
              Boolean parkingAvailable, Double rating, String imageUrl) {

        this.pgId = pgId;
        this.owner = owner;
        this.pgName = pgName;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.rentStarting = rentStarting;
        this.availableBeds = availableBeds;
        this.wifiAvailable = wifiAvailable;
        this.foodAvailable = foodAvailable;
        this.parkingAvailable = parkingAvailable;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters

    public Long getPgId() {
        return pgId;
    }

    public void setPgId(Long pgId) {
        this.pgId = pgId;
    }

    public String getPgName() {
        return pgName;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Double getRentStarting() {
        return rentStarting;
    }

    public void setRentStarting(Double rentStarting) {
        this.rentStarting = rentStarting;
    }

    public Integer getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(Integer availableBeds) {
        this.availableBeds = availableBeds;
    }

    public Boolean getWifiAvailable() {
        return wifiAvailable;
    }

    public void setWifiAvailable(Boolean wifiAvailable) {
        this.wifiAvailable = wifiAvailable;
    }

    public Boolean getFoodAvailable() {
        return foodAvailable;
    }

    public void setFoodAvailable(Boolean foodAvailable) {
        this.foodAvailable = foodAvailable;
    }

    public Boolean getParkingAvailable() {
        return parkingAvailable;
    }

    public void setParkingAvailable(Boolean parkingAvailable) {
        this.parkingAvailable = parkingAvailable;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}