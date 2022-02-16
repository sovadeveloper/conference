package com.sovadeveloper.conference.dto;

import com.sovadeveloper.conference.entities.ScheduleEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDTO {
    private Long id;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private RoomDTO room;
    private TalkDTO talk;

    public static ScheduleDTO toModel(ScheduleEntity scheduleEntity){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(scheduleEntity.getId());
        scheduleDTO.setDateStart(scheduleEntity.getDateStart());
        scheduleDTO.setDateEnd(scheduleEntity.getDateEnd());
        scheduleDTO.setRoom(RoomDTO.toModel(scheduleEntity.getRoom()));
        scheduleDTO.setTalk(TalkDTO.toModel(scheduleEntity.getTalk()));
        return scheduleDTO;
    }
}
