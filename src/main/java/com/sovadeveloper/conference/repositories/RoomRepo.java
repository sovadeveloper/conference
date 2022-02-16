package com.sovadeveloper.conference.repositories;

import com.sovadeveloper.conference.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<RoomEntity, Long> {
    RoomEntity findByNumber(int number);
}
