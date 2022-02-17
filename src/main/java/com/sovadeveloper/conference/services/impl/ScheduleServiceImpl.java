package com.sovadeveloper.conference.services.impl;

import com.sovadeveloper.conference.dto.ScheduleDTO;
import com.sovadeveloper.conference.dto.TalkDTO;
import com.sovadeveloper.conference.entities.ScheduleEntity;
import com.sovadeveloper.conference.repositories.RoomRepo;
import com.sovadeveloper.conference.repositories.ScheduleRepo;
import com.sovadeveloper.conference.repositories.TalkRepo;
import com.sovadeveloper.conference.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepo scheduleRepo;
    private final TalkRepo talkRepo;
    private final RoomRepo roomRepo;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepo scheduleRepo, TalkRepo talkRepo, RoomRepo roomRepo) {
        this.scheduleRepo = scheduleRepo;
        this.talkRepo = talkRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public ScheduleDTO create(ScheduleEntity scheduleEntity) throws Exception {
        System.out.println(scheduleEntity);
        List<ScheduleEntity> sameSchedule = scheduleRepo.findAllBetweenDates
                (scheduleEntity.getDateStart(), scheduleEntity.getDateEnd(), scheduleEntity.getRoom().getId());
        roomRepo.findById(scheduleEntity.getRoom().getId())
                .orElseThrow(() -> new Exception("Такой аудитории не существует"));
        talkRepo.findById(scheduleEntity.getTalk().getId())
                .orElseThrow(() -> new Exception("Такого доклада не существует"));
        if(scheduleEntity.getDateStart().isAfter(scheduleEntity.getDateEnd())){
            throw new Exception("Дата начала не может быть позже даты конца");
        }
        if(sameSchedule.size() > 0){
            throw new Exception("В данное время аудитория занята другими спикерами");
        }
        return ScheduleDTO.toModel(scheduleRepo.save(scheduleEntity));
    }

    @Override
    public ScheduleDTO getById(Long id) throws Exception {
        return ScheduleDTO.toModel(scheduleRepo.findById(id)
                .orElseThrow(() -> new Exception("Такой записи в расписании не существует")));
    }

    @Override
    public ScheduleDTO edit(Long id, ScheduleEntity scheduleEntityUpdated) throws Exception {
        ScheduleEntity scheduleEntity = scheduleRepo.findById(id)
                .orElseThrow(() -> new Exception("Такой записи в расписании не существует"));
        List<ScheduleEntity> sameSchedule = scheduleRepo.findAllBetweenDates
                (scheduleEntity.getDateStart(), scheduleEntity.getDateEnd(), scheduleEntity.getRoom().getId());
        roomRepo.findById(scheduleEntity.getRoom().getId())
                .orElseThrow(() -> new Exception("Такой аудитории не существует"));
        talkRepo.findById(scheduleEntity.getTalk().getId())
                .orElseThrow(() -> new Exception("Такого доклада не существует"));
        if(scheduleEntity.getDateStart().isAfter(scheduleEntity.getDateEnd())){
            throw new Exception("Дата начала не может быть позже даты конца");
        }
        if(sameSchedule.size() > 0){
            throw new Exception("В данное время аудитория занята другими спикерами");
        }
        scheduleEntity.setDateStart(scheduleEntityUpdated.getDateStart());
        scheduleEntity.setDateEnd(scheduleEntityUpdated.getDateEnd());
        scheduleEntity.setRoom(scheduleEntityUpdated.getRoom());
        scheduleEntity.setTalk(scheduleEntityUpdated.getTalk());
        return ScheduleDTO.toModel(scheduleRepo.save(scheduleEntity));
    }

    @Override
    public Long delete(Long id) throws Exception {
        ScheduleEntity scheduleEntity = scheduleRepo.findById(id)
                .orElseThrow(() -> new Exception("Такой записи в расписании не существует"));
        scheduleRepo.delete(scheduleEntity);
        return id;
    }

    @Override
    public List<ScheduleDTO> getAll() throws Exception {
        List<ScheduleEntity> scheduleEntities = scheduleRepo.findAll();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(ScheduleEntity scheduleEntity: scheduleEntities){
            scheduleDTOS.add(ScheduleDTO.toModel(scheduleEntity));
        }
        return scheduleDTOS;
    }

    @Override
    public List<ScheduleDTO> getScheduleByTalk(TalkDTO talkDTO) {
        List<ScheduleEntity> scheduleEntities = scheduleRepo.findAllByTalk(talkRepo.getById(talkDTO.getId()));
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(ScheduleEntity scheduleEntity: scheduleEntities){
            scheduleDTOS.add(ScheduleDTO.toModel(scheduleEntity));
        }
        return scheduleDTOS;
    }

    @Override
    public List<ScheduleDTO> getScheduleByRoom(Long id) {
        List<ScheduleEntity> scheduleEntities = scheduleRepo.findAllByRoom(roomRepo.getById(id));
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(ScheduleEntity scheduleEntity: scheduleEntities){
            scheduleDTOS.add(ScheduleDTO.toModel(scheduleEntity));
        }
        return scheduleDTOS;
    }
}
