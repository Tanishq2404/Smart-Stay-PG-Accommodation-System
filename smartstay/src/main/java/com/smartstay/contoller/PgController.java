package com.smartstay.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartstay.dto.ApiResponse;
import com.smartstay.entity.Pg;
import com.smartstay.services.PgService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pgs")
public class PgController {

    @Autowired
    private PgService pgService;

    // Add PG
    @PostMapping
    public ApiResponse addPg(@Valid @RequestBody Pg pg) {
        return pgService.addPg(pg);
    }

    // Get All PGs
    @GetMapping
    public List<Pg> getAllPgs() {
        return pgService.getAllPgs();
    }

    // Get PG By ID
    @GetMapping("/{id}")
    public Pg getPgById(@PathVariable Long id) {
        return pgService.getPgById(id);
    }

    // Search PG By City
    @GetMapping("/city/{city}")
    public List<Pg> getPgsByCity(@PathVariable String city) {
        return pgService.getPgsByCity(city);
    }

    // Top Rated PGs
    @GetMapping("/top-rated")
    public List<Pg> getTopRatedPgs() {
        return pgService.getTopRatedPgs();
    }

    // Search by Maximum Rent
    @GetMapping("/rent/{rent}")
    public List<Pg> getPgsByMaxRent(@PathVariable Double rent) {
        return pgService.getPgsByMaxRent(rent);
    }

    // PGs with WiFi
    @GetMapping("/wifi")
    public List<Pg> getWifiPgs() {
        return pgService.getWifiPgs();
    }

    // PGs with Food
    @GetMapping("/food")
    public List<Pg> getFoodPgs() {
        return pgService.getFoodPgs();
    }

    // PGs with Parking
    @GetMapping("/parking")
    public List<Pg> getParkingPgs() {
        return pgService.getParkingPgs();
    }

    // Get PGs By Owner
    @GetMapping("/owner/{ownerId}")
    public List<Pg> getPgsByOwner(@PathVariable Long ownerId) {
        return pgService.getPgsByOwner(ownerId);
    }

    // Update PG
    @PutMapping("/{id}")
    public ApiResponse updatePg(@PathVariable Long id,
                                @Valid @RequestBody Pg pg) {
        return pgService.updatePg(id, pg);
    }

    // Delete PG
    @DeleteMapping("/{id}")
    public ApiResponse deletePg(@PathVariable Long id) {
        return pgService.deletePg(id);
    }
}