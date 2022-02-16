package com.sovadeveloper.conference.dto;

import com.sovadeveloper.conference.entities.RoomEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    private int number;

    public static RoomDTO toModel(RoomEntity roomEntity){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(roomEntity.getId());
        roomDTO.setNumber(roomEntity.getNumber());
        return roomDTO;
    }
}
