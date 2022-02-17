package com.sovadeveloper.conference.services.impl;

import com.sovadeveloper.conference.dto.RoomDTO;
import com.sovadeveloper.conference.entities.RoomEntity;
import com.sovadeveloper.conference.repositories.RoomRepo;
import com.sovadeveloper.conference.services.RoomService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {
    @Autowired
    @InjectMocks
    private RoomServiceImpl roomService;
    RoomEntity roomEntity = new RoomEntity();
    List<RoomEntity> roomEntityList = new ArrayList<>();

    @Mock
    private RoomRepo roomRepo;

    @BeforeEach
    public void setUp(){
        roomEntity.setId(1L);
        roomEntity.setNumber(15);
        roomEntityList.add(roomEntity);
    }

    @Test
    void create() throws Exception {
        when(roomRepo.save(any())).thenReturn(roomEntity);
        RoomDTO roomDTO = roomService.create(roomEntity);
        verify(roomRepo, Mockito.times(1)).save(any());
        assertEquals(roomDTO.getId(), roomEntity.getId());
    }

    @Test
    void getAll() throws Exception {
        roomRepo.save(roomEntity);
        when(roomRepo.findAll()).thenReturn(roomEntityList);
        List<RoomDTO> roomDTOS = roomService.getAll();
        assertEquals(roomDTOS.size(), roomEntityList.size());
        verify(roomRepo, times(1)).save(roomEntity);
        verify(roomRepo, times(1)).findAll();
    }

    @Test
    void getById() throws Exception {
        when(roomRepo.findById(1L)).thenReturn(Optional.ofNullable(roomEntity));
        RoomDTO roomDTO = roomService.getById(1L);
        verify(roomRepo, Mockito.times(1)).findById(any());
        assertEquals(roomDTO.getId(), roomEntity.getId());
    }
}