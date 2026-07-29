package com.smartstay.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartstay.dto.ApiResponse;
import com.smartstay.dto.AuthResponse;
import com.smartstay.dto.LoginRequest;
import com.smartstay.dto.OwnerResponse;
import com.smartstay.entity.Owner;
import com.smartstay.services.OwnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    // Register Owner
    @PostMapping("/register")
    public ApiResponse registerOwner(@Valid @RequestBody Owner owner) {
        return ownerService.registerOwner(owner);
    }

    // Login Owner
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return ownerService.login(request);
    }

    // Get All Owners
    @GetMapping
    public List<OwnerResponse> getAllOwners() {
        return ownerService.getAllOwners();
    }

    // Get Owner By ID
    @GetMapping("/{id}")
    public OwnerResponse getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    // Update Owner
    @PutMapping("/{id}")
    public ApiResponse updateOwner(@PathVariable Long id,
                                   @Valid @RequestBody Owner owner) {
        return ownerService.updateOwner(id, owner);
    }

    // Delete Owner
    @DeleteMapping("/{id}")
    public ApiResponse deleteOwner(@PathVariable Long id) {
        return ownerService.deleteOwner(id);
    }
}