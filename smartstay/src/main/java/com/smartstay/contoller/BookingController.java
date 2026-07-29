package com.smartstay.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartstay.dto.ApiResponse;
import com.smartstay.entity.Booking;
import com.smartstay.services.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Book Room
    @PostMapping
    public ApiResponse bookRoom(@Valid @RequestBody Booking booking) {
        return bookingService.bookRoom(booking);
    }

    // Get All Bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Get Booking By ID
    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    // Get Bookings By User
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    // Get Bookings By Room
    @GetMapping("/room/{roomId}")
    public List<Booking> getBookingsByRoom(@PathVariable Long roomId) {
        return bookingService.getBookingsByRoom(roomId);
    }

    // Get Bookings By Status
    @GetMapping("/status/{status}")
    public List<Booking> getBookingsByStatus(@PathVariable String status) {
        return bookingService.getBookingsByStatus(status);
    }

    // Count Bookings By Status
    @GetMapping("/count/{status}")
    public long countBookingsByStatus(@PathVariable String status) {
        return bookingService.countBookingsByStatus(status);
    }

    // Cancel Booking
    @PutMapping("/cancel/{bookingId}")
    public ApiResponse cancelBooking(@PathVariable Long bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    // Delete Booking (Admin)
    @DeleteMapping("/{bookingId}")
    public ApiResponse deleteBooking(@PathVariable Long bookingId) {
        return bookingService.deleteBooking(bookingId);
    }
}