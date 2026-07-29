package com.smartstay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartstay.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    // Find Owner by Email
    Owner findByEmail(String email);

    // Check if Email already exists
    boolean existsByEmail(String email);

    // Check if Mobile Number already exists
    boolean existsByMobileNumber(String mobileNumber);

}