package com.smartstay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartstay.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Get All Bookings of a User
    List<Booking> findByUserId(Long userId);

    // Get All Bookings of a Room
    List<Booking> findByRoomId(Long roomId);

    // Prevent Duplicate Active Booking
    boolean existsByUserIdAndRoomIdAndStatus(
            Long userId,
            Long roomId,
            String status);

    // Get Bookings By Status
    List<Booking> findByStatus(String status);

    // Count Bookings By Status
    long countByStatus(String status);

}