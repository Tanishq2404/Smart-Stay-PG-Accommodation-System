package com.smartstay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartstay.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);

}