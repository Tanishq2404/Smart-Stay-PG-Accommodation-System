package com.smartstay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartstay.entity.Pg;

@Repository
public interface PgRepository extends JpaRepository<Pg, Long> {

    // Search PGs by City
    List<Pg> findByCity(String city);

    // Top 5 Rated PGs
    List<Pg> findTop5ByOrderByRatingDesc();

    // Search by Maximum Starting Rent
    List<Pg> findByRentStartingLessThanEqual(Double rentStarting);

    // PGs with WiFi
    List<Pg> findByWifiAvailableTrue();

    // PGs with Food
    List<Pg> findByFoodAvailableTrue();

    // PGs with Parking
    List<Pg> findByParkingAvailableTrue();

    // Get All PGs of a Particular Owner
    List<Pg> findByOwnerOwnerId(Long ownerId);

}