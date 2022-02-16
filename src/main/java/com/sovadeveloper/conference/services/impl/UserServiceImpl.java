package com.sovadeveloper.conference.services.impl;

import com.sovadeveloper.conference.dto.UserDTO;
import com.sovadeveloper.conference.entities.Role;
import com.sovadeveloper.conference.entities.UserEntity;
import com.sovadeveloper.conference.repositories.UserRepo;
import com.sovadeveloper.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    @Override
    public UserDTO create(UserEntity userEntity) throws Exception {
        return UserDTO.toModel(userRepo.save(userEntity));
    }

    @Override
    public UserDTO getById(Long id) throws Exception {
        return UserDTO.toModel(userRepo.findById(id)
        .orElseThrow(() -> new Exception("Такого пользователя не существует")));
    }

    @Override
    public UserDTO edit(UserDTO userDTOUpdated) throws Exception {
        UserEntity userEntity = userRepo.findById(userDTOUpdated.getId())
                .orElseThrow(() -> new Exception("Такого пользователя не существует"));
        userEntity.setRole(Role.SPEAKER);
        return UserDTO.toModel(userRepo.save(userEntity));
    }

    @Override
    public Long delete(Long id) throws Exception {
        UserEntity userEntity = userRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого пользователя не существует"));
        userRepo.delete(userEntity);
        return id;
    }

    @Override
    public List<UserDTO> getAll() throws Exception {
        List<UserEntity> userEntities = userRepo.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(UserEntity userEntity: userEntities){
            userDTOS.add(UserDTO.toModel(userEntity));
        }
        return userDTOS;
    }
}
