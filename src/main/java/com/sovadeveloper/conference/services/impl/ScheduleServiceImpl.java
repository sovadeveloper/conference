package com.sovadeveloper.conference.services.impl;

import com.sovadeveloper.conference.dto.ScheduleDTO;
import com.sovadeveloper.conference.entities.ScheduleEntity;
import com.sovadeveloper.conference.repositories.ScheduleRepo;
import com.sovadeveloper.conference.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepo scheduleRepo;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public ScheduleDTO create(ScheduleEntity scheduleEntity) throws Exception {
        List<ScheduleEntity> sameSchedule = scheduleRepo.findAllByDateStartGreaterThanEqualAndDateEndLessThanEqual
                (scheduleEntity.getDateStart(), scheduleEntity.getDateEnd());
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
}
