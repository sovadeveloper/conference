package com.sovadeveloper.conference.dto;

import com.sovadeveloper.conference.entities.TalkEntity;
import com.sovadeveloper.conference.entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TalkDTO {
    private Long id;
    private String title;
    private String text;
    private Set<UserDTO> users;

    public static TalkDTO toModel(TalkEntity talkEntity){
        TalkDTO talkDTO = new TalkDTO();
        talkDTO.setId(talkEntity.getId());
        talkDTO.setTitle(talkEntity.getTitle());
        talkDTO.setText(talkEntity.getText());
        Set<UserDTO> userDTOS = new HashSet<>();
        for(UserEntity userEntity: talkEntity.getUsers()){
            userDTOS.add(UserDTO.toModel(userEntity));
        }
        talkDTO.setUsers(userDTOS);
        return talkDTO;
    }
}
