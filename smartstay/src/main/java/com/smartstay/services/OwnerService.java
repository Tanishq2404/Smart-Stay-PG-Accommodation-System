package com.smartstay.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartstay.dto.ApiResponse;
import com.smartstay.dto.AuthResponse;
import com.smartstay.dto.LoginRequest;
import com.smartstay.dto.OwnerResponse;
import com.smartstay.entity.Owner;
import com.smartstay.repository.OwnerRepository;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    // Register Owner
    public ApiResponse registerOwner(Owner owner) {

        if (ownerRepository.existsByEmail(owner.getEmail())) {
            return new ApiResponse(false, "Email already registered");
        }

        if (ownerRepository.existsByMobileNumber(owner.getMobileNumber())) {
            return new ApiResponse(false, "Mobile number already registered");
        }

        ownerRepository.save(owner);

        return new ApiResponse(true, "Owner Registered Successfully");
    }

    // Login Owner
    public AuthResponse login(LoginRequest request) {

        Owner owner = ownerRepository.findByEmail(request.getEmail());

        if (owner == null) {
            return new AuthResponse(false, "Owner not found", null);
        }

        // Future: Replace with BCrypt Password Encoder
        if (!owner.getPassword().equals(request.getPassword())) {
            return new AuthResponse(false, "Invalid Password", null);
        }

        return new AuthResponse(
                true,
                "Login Successful",
                mapToOwnerResponse(owner));
    }

    // Get All Owners
    public List<OwnerResponse> getAllOwners() {

        List<Owner> owners = ownerRepository.findAll();
        List<OwnerResponse> responseList = new ArrayList<>();

        for (Owner owner : owners) {
            responseList.add(mapToOwnerResponse(owner));
        }

        return responseList;
    }

    // Get Owner By ID
    public OwnerResponse getOwnerById(Long id) {

        Owner owner = ownerRepository.findById(id).orElse(null);

        if (owner == null) {
            return null;
        }

        return mapToOwnerResponse(owner);
    }

    // Update Owner
    public ApiResponse updateOwner(Long id, Owner updatedOwner) {

        Owner owner = ownerRepository.findById(id).orElse(null);

        if (owner == null) {
            return new ApiResponse(false, "Owner not found");
        }

        if (!owner.getEmail().equals(updatedOwner.getEmail())
                && ownerRepository.existsByEmail(updatedOwner.getEmail())) {

            return new ApiResponse(false, "Email already registered");
        }

        if (!owner.getMobileNumber().equals(updatedOwner.getMobileNumber())
                && ownerRepository.existsByMobileNumber(updatedOwner.getMobileNumber())) {

            return new ApiResponse(false, "Mobile number already registered");
        }

        owner.setFullName(updatedOwner.getFullName());
        owner.setEmail(updatedOwner.getEmail());
        owner.setPassword(updatedOwner.getPassword());
        owner.setMobileNumber(updatedOwner.getMobileNumber());

        ownerRepository.save(owner);

        return new ApiResponse(true, "Owner Updated Successfully");
    }

    // Delete Owner
    public ApiResponse deleteOwner(Long id) {

        if (!ownerRepository.existsById(id)) {
            return new ApiResponse(false, "Owner not found");
        }

        ownerRepository.deleteById(id);

        return new ApiResponse(true, "Owner Deleted Successfully");
    }

    // Convert Owner Entity to OwnerResponse DTO
    private OwnerResponse mapToOwnerResponse(Owner owner) {

        OwnerResponse response = new OwnerResponse();

        response.setOwnerId(owner.getOwnerId());
        response.setFullName(owner.getFullName());
        response.setEmail(owner.getEmail());
        response.setMobileNumber(owner.getMobileNumber());

        return response;
    }
}