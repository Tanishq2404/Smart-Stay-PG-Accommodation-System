package com.smartstay.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartstay.dto.ApiResponse;
import com.smartstay.entity.Owner;
import com.smartstay.entity.Pg;
import com.smartstay.repository.OwnerRepository;
import com.smartstay.repository.PgRepository;

@Service
public class PgService {

    @Autowired
    private PgRepository pgRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    // Add PG
    public ApiResponse addPg(Pg pg) {

        if (pg.getOwner() == null || pg.getOwner().getOwnerId() == null) {
            return new ApiResponse(false, "Owner is required");
        }

        Optional<Owner> owner = ownerRepository.findById(pg.getOwner().getOwnerId());

        if (owner.isEmpty()) {
            return new ApiResponse(false, "Owner not found");
        }

        pg.setOwner(owner.get());

        pgRepository.save(pg);

        return new ApiResponse(true, "PG added successfully");
    }

    // Get All PGs
    public List<Pg> getAllPgs() {
        return pgRepository.findAll();
    }

    // Get PG By ID
    public Pg getPgById(Long id) {
        return pgRepository.findById(id).orElse(null);
    }

    // Search PGs By City
    public List<Pg> getPgsByCity(String city) {
        return pgRepository.findByCity(city);
    }

    // Top Rated PGs
    public List<Pg> getTopRatedPgs() {
        return pgRepository.findTop5ByOrderByRatingDesc();
    }

    // Search By Maximum Rent
    public List<Pg> getPgsByMaxRent(Double rent) {
        return pgRepository.findByRentStartingLessThanEqual(rent);
    }

    // PGs with WiFi
    public List<Pg> getWifiPgs() {
        return pgRepository.findByWifiAvailableTrue();
    }

    // PGs with Food
    public List<Pg> getFoodPgs() {
        return pgRepository.findByFoodAvailableTrue();
    }

    // PGs with Parking
    public List<Pg> getParkingPgs() {
        return pgRepository.findByParkingAvailableTrue();
    }

    // Get PGs By Owner
    public List<Pg> getPgsByOwner(Long ownerId) {
        return pgRepository.findByOwnerOwnerId(ownerId);
    }

    // Update PG
    public ApiResponse updatePg(Long id, Pg updatedPg) {

        Optional<Pg> optionalPg = pgRepository.findById(id);

        if (optionalPg.isEmpty()) {
            return new ApiResponse(false, "PG not found");
        }

        Pg pg = optionalPg.get();

        if (updatedPg.getOwner() != null && updatedPg.getOwner().getOwnerId() != null) {

            Optional<Owner> owner = ownerRepository.findById(updatedPg.getOwner().getOwnerId());

            if (owner.isEmpty()) {
                return new ApiResponse(false, "Owner not found");
            }

            pg.setOwner(owner.get());
        }

        pg.setPgName(updatedPg.getPgName());
        pg.setDescription(updatedPg.getDescription());
        pg.setAddress(updatedPg.getAddress());
        pg.setCity(updatedPg.getCity());
        pg.setState(updatedPg.getState());
        pg.setPincode(updatedPg.getPincode());
        pg.setRentStarting(updatedPg.getRentStarting());
        pg.setAvailableBeds(updatedPg.getAvailableBeds());
        pg.setWifiAvailable(updatedPg.getWifiAvailable());
        pg.setFoodAvailable(updatedPg.getFoodAvailable());
        pg.setParkingAvailable(updatedPg.getParkingAvailable());
        pg.setRating(updatedPg.getRating());
        pg.setImageUrl(updatedPg.getImageUrl());

        pgRepository.save(pg);

        return new ApiResponse(true, "PG updated successfully");
    }

    // Delete PG
    public ApiResponse deletePg(Long id) {

        if (!pgRepository.existsById(id)) {
            return new ApiResponse(false, "PG not found");
        }

        pgRepository.deleteById(id);

        return new ApiResponse(true, "PG deleted successfully");
    }
}