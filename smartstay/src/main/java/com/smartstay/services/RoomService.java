package com.smartstay.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartstay.dto.ApiResponse;
import com.smartstay.entity.Pg;
import com.smartstay.entity.Room;
import com.smartstay.repository.PgRepository;
import com.smartstay.repository.RoomRepository;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PgRepository pgRepository;

    // Add Room
    public ApiResponse addRoom(Room room) {

        if (room.getPg() == null || room.getPg().getPgId() == null) {
            return new ApiResponse(false, "PG is required");
        }

        Optional<Pg> pg = pgRepository.findById(room.getPg().getPgId());

        if (pg.isEmpty()) {
            return new ApiResponse(false, "PG not found");
        }

        if (room.getAvailableBeds() > room.getTotalBeds()) {
            return new ApiResponse(false,
                    "Available beds cannot be greater than total beds");
        }

        room.setPg(pg.get());

        roomRepository.save(room);

        return new ApiResponse(true, "Room added successfully");
    }

    // Get All Rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get Room By ID
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    // Get Rooms By PG ID
    public List<Room> getRoomsByPgId(Long pgId) {
        return roomRepository.findByPg_PgId(pgId);
    }

    // Get Available Rooms
    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailableBedsGreaterThan(0);
    }

    // Get Rooms By Type
    public List<Room> getRoomsByType(String roomType) {
        return roomRepository.findByRoomType(roomType);
    }

    // Get AC Rooms
    public List<Room> getAcRooms() {
        return roomRepository.findByAcAvailableTrue();
    }

    // Get Rooms with Attached Bathroom
    public List<Room> getAttachedBathroomRooms() {
        return roomRepository.findByAttachedBathroomTrue();
    }

    // Update Room
    public ApiResponse updateRoom(Long id, Room updatedRoom) {

        Optional<Room> optionalRoom = roomRepository.findById(id);

        if (optionalRoom.isEmpty()) {
            return new ApiResponse(false, "Room not found");
        }

        if (updatedRoom.getAvailableBeds() > updatedRoom.getTotalBeds()) {
            return new ApiResponse(false,
                    "Available beds cannot be greater than total beds");
        }

        Room room = optionalRoom.get();

        if (updatedRoom.getPg() != null &&
                updatedRoom.getPg().getPgId() != null) {

            Optional<Pg> pg =
                    pgRepository.findById(updatedRoom.getPg().getPgId());

            if (pg.isEmpty()) {
                return new ApiResponse(false, "PG not found");
            }

            room.setPg(pg.get());
        }

        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setRoomType(updatedRoom.getRoomType());
        room.setRent(updatedRoom.getRent());
        room.setTotalBeds(updatedRoom.getTotalBeds());
        room.setAvailableBeds(updatedRoom.getAvailableBeds());
        room.setAcAvailable(updatedRoom.getAcAvailable());
        room.setAttachedBathroom(updatedRoom.getAttachedBathroom());

        roomRepository.save(room);

        return new ApiResponse(true, "Room updated successfully");
    }

    // Delete Room
    public ApiResponse deleteRoom(Long id) {

        if (!roomRepository.existsById(id)) {
            return new ApiResponse(false, "Room not found");
        }

        roomRepository.deleteById(id);

        return new ApiResponse(true, "Room deleted successfully");
    }
}