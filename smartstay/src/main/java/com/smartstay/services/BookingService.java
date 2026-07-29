package com.smartstay.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartstay.dto.ApiResponse;
import com.smartstay.entity.Booking;
import com.smartstay.entity.Room;
import com.smartstay.repository.BookingRepository;
import com.smartstay.repository.RoomRepository;
import com.smartstay.repository.UserRepository;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    private static final String CONFIRMED = "CONFIRMED";
    private static final String CANCELLED = "CANCELLED";

    // Book Room
    public ApiResponse bookRoom(Booking booking) {

        // Check User Exists
        if (!userRepository.existsById(booking.getUserId())) {
            return new ApiResponse(false, "User not found");
        }

        // Check Room Exists
        Room room = roomRepository.findById(booking.getRoomId()).orElse(null);

        if (room == null) {
            return new ApiResponse(false, "Room not found");
        }

        // Check Available Beds
        if (room.getAvailableBeds() <= 0) {
            return new ApiResponse(false, "No beds available");
        }

        // Prevent Duplicate Active Booking
        if (bookingRepository.existsByUserIdAndRoomIdAndStatus(
                booking.getUserId(),
                booking.getRoomId(),
                CONFIRMED)) {

            return new ApiResponse(false,
                    "You already have an active booking for this room");
        }

        // Save Booking
        booking.setBookingDate(LocalDate.now());
        booking.setStatus(CONFIRMED);

        bookingRepository.save(booking);

        // Decrease Available Beds
        room.setAvailableBeds(room.getAvailableBeds() - 1);
        roomRepository.save(room);

        return new ApiResponse(true, "Room booked successfully");
    }

    // Get All Bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get Booking By ID
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    // Get Bookings By User
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    // Get Bookings By Room
    public List<Booking> getBookingsByRoom(Long roomId) {
        return bookingRepository.findByRoomId(roomId);
    }

    // Get Bookings By Status
    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    // Count Bookings By Status
    public long countBookingsByStatus(String status) {
        return bookingRepository.countByStatus(status);
    }

    // Cancel Booking
    public ApiResponse cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId).orElse(null);

        if (booking == null) {
            return new ApiResponse(false, "Booking not found");
        }

        if (CANCELLED.equals(booking.getStatus())) {
            return new ApiResponse(false, "Booking already cancelled");
        }

        // Increase Available Beds
        Room room = roomRepository.findById(booking.getRoomId()).orElse(null);

        if (room != null) {

            if (room.getAvailableBeds() < room.getTotalBeds()) {
                room.setAvailableBeds(room.getAvailableBeds() + 1);
                roomRepository.save(room);
            }
        }

        booking.setStatus(CANCELLED);
        bookingRepository.save(booking);

        return new ApiResponse(true, "Booking cancelled successfully");
    }

    // Delete Booking (Optional - Admin Use)
    public ApiResponse deleteBooking(Long bookingId) {

        if (!bookingRepository.existsById(bookingId)) {
            return new ApiResponse(false, "Booking not found");
        }

        bookingRepository.deleteById(bookingId);

        return new ApiResponse(true, "Booking deleted successfully");
    }
}