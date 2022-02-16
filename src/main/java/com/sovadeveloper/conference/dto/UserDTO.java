package com.sovadeveloper.conference.dto;

import com.sovadeveloper.conference.entities.Role;
import com.sovadeveloper.conference.entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private boolean active;
    private Role role;

    public static UserDTO toModel(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setActive(userEntity.isActive());
        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }
}
