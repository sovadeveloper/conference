package com.sovadeveloper.conference.services;

import com.sovadeveloper.conference.dto.UserDTO;
import com.sovadeveloper.conference.entities.UserEntity;

import java.util.List;

public interface UserService {
    UserDTO create(UserEntity userEntity) throws Exception;
    UserDTO getById(Long id) throws Exception;
    UserDTO edit(UserDTO userDTOUpdated) throws Exception;
    Long delete(Long id) throws Exception;
    List<UserDTO> getAll() throws Exception;
}
