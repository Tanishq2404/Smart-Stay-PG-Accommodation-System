package com.smartstay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartstay.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // Get All Rooms of a Particular PG
    List<Room> findByPg_PgId(Long pgId);

    // Rooms Having Available Beds
    List<Room> findByAvailableBedsGreaterThan(Integer availableBeds);

    // Search by Room Type
    List<Room> findByRoomType(String roomType);

    // AC Rooms
    List<Room> findByAcAvailableTrue();

    // Rooms with Attached Bathroom
    List<Room> findByAttachedBathroomTrue();

}