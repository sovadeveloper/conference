package com.sovadeveloper.conference.services.impl;

import com.sovadeveloper.conference.dto.TalkDTO;
import com.sovadeveloper.conference.dto.UserDTO;
import com.sovadeveloper.conference.entities.Role;
import com.sovadeveloper.conference.entities.UserEntity;
import com.sovadeveloper.conference.repositories.TalkRepo;
import com.sovadeveloper.conference.repositories.UserRepo;
import com.sovadeveloper.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepo userRepo;
    private final TalkRepo talkRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, TalkRepo talkRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.talkRepo = talkRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    @Override
    public UserDTO create(UserEntity userEntity) throws Exception {
        UserEntity userEntityFromDb = userRepo.findByUsername(userEntity.getUsername());
        if(userEntityFromDb != null){
            throw new Exception("Такой пользователь уже существует");
        }
        if(userEntity.getPassword().length() < 1){
            throw new Exception("Пароль не может быть пустым");
        }
        userEntity.setActive(true);
        userEntity.setRole(Role.LISTENER);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepo.save(userEntity);
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

    @Override
    public List<UserDTO> getFreeSpeakersForTalk(TalkDTO talkDTO) {
        List<UserEntity> freeSpeakers = userRepo.findAllByTalksNotContainingAndRole(talkRepo.getById(talkDTO.getId()), Role.SPEAKER);
        List<UserDTO> userDTOS = new ArrayList<>();
        for(UserEntity userEntity: freeSpeakers){
            userDTOS.add(UserDTO.toModel(userEntity));
        }
        return userDTOS;
    }
}
