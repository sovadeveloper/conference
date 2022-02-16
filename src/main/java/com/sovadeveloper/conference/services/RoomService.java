package com.sovadeveloper.conference.services;

import com.sovadeveloper.conference.dto.RoomDTO;
import com.sovadeveloper.conference.dto.ScheduleDTO;
import com.sovadeveloper.conference.entities.RoomEntity;

import java.util.List;

public interface RoomService {
    RoomDTO create(RoomEntity roomEntity) throws Exception;
    RoomDTO getById(Long id) throws Exception;
    RoomDTO edit(Long id, RoomEntity roomEntityUpdated) throws Exception;
    Long delete(Long id) throws Exception;
    List<RoomDTO> getAll() throws Exception;
    List<ScheduleDTO> getScheduleByRoom(Long id) throws Exception;
}
