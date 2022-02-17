package com.sovadeveloper.conference.repositories;

import com.sovadeveloper.conference.entities.RoomEntity;
import com.sovadeveloper.conference.entities.ScheduleEntity;
import com.sovadeveloper.conference.entities.TalkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepo extends JpaRepository<ScheduleEntity, Long> {
    @Query(value = "SELECT * FROM schedule WHERE (ROOM_ID = :roomId AND ((DATE_START <= :dateStart AND DATE_END >= :dateEnd)" +
            " OR (DATE_START <= :dateEnd AND DATE_END >= :dateEnd)" +
            " OR (DATE_START <= :dateStart AND DATE_END >= :dateStart)))", nativeQuery = true)
    List<ScheduleEntity> findAllBetweenDates
            (@Param("dateStart") LocalDateTime dateStart, @Param("dateEnd") LocalDateTime dateEnd,
             @Param("roomId") Long roomId);

    List<ScheduleEntity> findAllByRoom(RoomEntity roomEntity);
    List<ScheduleEntity> findAllByTalk(TalkEntity talkEntity);
}
