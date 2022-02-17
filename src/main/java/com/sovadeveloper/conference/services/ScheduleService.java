package com.sovadeveloper.conference.services;

import com.sovadeveloper.conference.dto.ScheduleDTO;
import com.sovadeveloper.conference.dto.TalkDTO;
import com.sovadeveloper.conference.entities.ScheduleEntity;

import java.util.List;

public interface ScheduleService {
    ScheduleDTO create(ScheduleEntity scheduleEntity) throws Exception;
    ScheduleDTO getById(Long id) throws Exception;
    ScheduleDTO edit(Long id, ScheduleEntity scheduleEntityUpdated) throws Exception;
    Long delete(Long id) throws Exception;
    List<ScheduleDTO> getAll() throws Exception;
    List<ScheduleDTO> getScheduleByTalk(TalkDTO talkDTO);
    List<ScheduleDTO> getScheduleByRoom(Long id);
}
