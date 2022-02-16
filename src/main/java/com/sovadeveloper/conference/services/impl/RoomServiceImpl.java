package com.sovadeveloper.conference.services.impl;

import com.sovadeveloper.conference.dto.RoomDTO;
import com.sovadeveloper.conference.dto.ScheduleDTO;
import com.sovadeveloper.conference.entities.RoomEntity;
import com.sovadeveloper.conference.entities.ScheduleEntity;
import com.sovadeveloper.conference.repositories.RoomRepo;
import com.sovadeveloper.conference.repositories.ScheduleRepo;
import com.sovadeveloper.conference.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;
    private final ScheduleRepo scheduleRepo;

    @Autowired
    public RoomServiceImpl(RoomRepo roomRepo, ScheduleRepo scheduleRepo) {
        this.roomRepo = roomRepo;
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public RoomDTO create(RoomEntity roomEntity) throws Exception {
        RoomEntity roomEntityWithSameNumber = roomRepo.findByNumber(roomEntity.getNumber());
        if(roomEntityWithSameNumber != null){
            throw new Exception("Аудитория с данным номером уже сущесвует");
        }
        return RoomDTO.toModel(roomRepo.save(roomEntity));
    }

    @Override
    public RoomDTO getById(Long id) throws Exception {
        return RoomDTO.toModel(roomRepo.findById(id)
                .orElseThrow(() -> new Exception("Такой комнаты не существует")));
    }

    @Override
    public RoomDTO edit(Long id, RoomEntity roomEntityUpdated) throws Exception {
        RoomEntity roomEntity = roomRepo.findById(id)
                .orElseThrow(() -> new Exception("Такой комнаты не существует"));
        RoomEntity roomEntityWithSameNumber = roomRepo.findByNumber(roomEntityUpdated.getNumber());
        if(roomEntityWithSameNumber != null){
            throw new Exception("Аудитория с данным номером уже сущесвует");
        }
        roomEntity.setNumber(roomEntityUpdated.getNumber());
        return RoomDTO.toModel(roomRepo.save(roomEntity));
    }

    @Override
    public Long delete(Long id) throws Exception {
        RoomEntity roomEntity = roomRepo.findById(id)
                .orElseThrow(() -> new Exception("Такой комнаты не существует"));
        roomRepo.delete(roomEntity);
        return id;
    }

    @Override
    public List<RoomDTO> getAll() throws Exception {
        List<RoomEntity> roomEntities = roomRepo.findAll();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for(RoomEntity roomEntity: roomEntities){
            roomDTOS.add(RoomDTO.toModel(roomEntity));
        }
        return roomDTOS;
    }

    @Override
    public List<ScheduleDTO> getScheduleByRoom(Long id) throws Exception {
        RoomEntity roomEntity = roomRepo.getById(id);
        List<ScheduleEntity> scheduleEntities = scheduleRepo.findAllByRoom(roomEntity);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(ScheduleEntity scheduleEntity: scheduleEntities){
            scheduleDTOS.add(ScheduleDTO.toModel(scheduleEntity));
        }
        return scheduleDTOS;
    }
}
