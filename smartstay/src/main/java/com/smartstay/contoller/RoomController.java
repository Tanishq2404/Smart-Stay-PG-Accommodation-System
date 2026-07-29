package com.smartstay.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartstay.dto.ApiResponse;
import com.smartstay.entity.Room;
import com.smartstay.services.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Add Room
    @PostMapping
    public ApiResponse addRoom(@Valid @RequestBody Room room) {
        return roomService.addRoom(room);
    }

    // Get All Rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    // Get Room By ID
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    // Get Rooms By PG ID
    @GetMapping("/pg/{pgId}")
    public List<Room> getRoomsByPgId(@PathVariable Long pgId) {
        return roomService.getRoomsByPgId(pgId);
    }

    // Get Available Rooms
    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    // Get Rooms By Type
    @GetMapping("/type/{roomType}")
    public List<Room> getRoomsByType(@PathVariable String roomType) {
        return roomService.getRoomsByType(roomType);
    }

    // Get AC Rooms
    @GetMapping("/ac")
    public List<Room> getAcRooms() {
        return roomService.getAcRooms();
    }

    // Get Rooms with Attached Bathroom
    @GetMapping("/attached-bathroom")
    public List<Room> getAttachedBathroomRooms() {
        return roomService.getAttachedBathroomRooms();
    }

    // Update Room
    @PutMapping("/{id}")
    public ApiResponse updateRoom(@PathVariable Long id,
                                  @Valid @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    // Delete Room
    @DeleteMapping("/{id}")
    public ApiResponse deleteRoom(@PathVariable Long id) {
        return roomService.deleteRoom(id);
    }
}