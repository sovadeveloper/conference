package com.sovadeveloper.conference.repositories;

import com.sovadeveloper.conference.entities.RoomEntity;
import com.sovadeveloper.conference.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepo extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAllByDateStartGreaterThanEqualAndDateEndLessThanEqual
            (LocalDateTime start, LocalDateTime finish);
    List<ScheduleEntity> findAllByRoom(RoomEntity roomEntity);
}
