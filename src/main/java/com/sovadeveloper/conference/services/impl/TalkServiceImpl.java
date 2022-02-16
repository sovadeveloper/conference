package com.sovadeveloper.conference.services.impl;

import com.sovadeveloper.conference.dto.TalkDTO;
import com.sovadeveloper.conference.dto.UserDTO;
import com.sovadeveloper.conference.entities.TalkEntity;
import com.sovadeveloper.conference.entities.UserEntity;
import com.sovadeveloper.conference.repositories.TalkRepo;
import com.sovadeveloper.conference.repositories.UserRepo;
import com.sovadeveloper.conference.services.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TalkServiceImpl implements TalkService {
    private final TalkRepo talkRepo;
    private final UserRepo userRepo;

    @Autowired
    public TalkServiceImpl(TalkRepo talkRepo, UserRepo userRepo) {
        this.talkRepo = talkRepo;
        this.userRepo = userRepo;
    }

    @Override
    public TalkDTO create(TalkEntity talkEntity, UserEntity userEntity) throws Exception {
        if(talkEntity.getTitle().length() < 1){
            throw new Exception("Заголовок доклада не может быть пустым");
        }
        if(talkEntity.getText().length() < 1){
            throw new Exception("Текст доклада не может быть пустым");
        }
        talkEntity.addUser(userEntity);
        return TalkDTO.toModel(talkRepo.save(talkEntity));
    }

    @Override
    public TalkDTO getById(Long id) throws Exception {
        return TalkDTO.toModel(talkRepo.findById(id)
        .orElseThrow(() -> new Exception("Такого доклада не существует")));
    }

    @Override
    public TalkDTO edit(Long id, TalkEntity talkEntityUpdated) throws Exception {
        TalkEntity talkEntity = talkRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого доклада не существует"));
        if(talkEntityUpdated.getTitle().length() < 1){
            throw new Exception("Заголовок доклада не может быть пустым");
        }
        if(talkEntityUpdated.getText().length() < 1){
            throw new Exception("Текст доклада не может быть пустым");
        }
        talkEntity.setText(talkEntityUpdated.getText());
        talkEntity.setTitle(talkEntityUpdated.getTitle());
        return TalkDTO.toModel(talkRepo.save(talkEntity));
    }

    @Override
    public Long delete(Long id) throws Exception {
        TalkEntity talkEntity = talkRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого доклада не существует"));
        talkRepo.delete(talkEntity);
        return id;
    }

    @Override
    public TalkDTO deleteUserFromTalk(TalkEntity talkEntityUpdated) throws Exception {
        TalkEntity talkEntityFromDB = talkRepo.findById(talkEntityUpdated.getId())
                .orElseThrow(() -> new Exception("Такого доклада не существует"));
        talkEntityFromDB.deleteUser(userRepo.getById(talkEntityFromDB.getUsers().stream().findFirst().get().getId()));
        return TalkDTO.toModel(talkRepo.save(talkEntityFromDB));
    }

    @Override
    public List<TalkDTO> getAll() throws Exception {
        List<TalkEntity> talkEntities = talkRepo.findAll();
        List<TalkDTO> talkDTOS = new ArrayList<>();
        for (TalkEntity talkEntity: talkEntities){
            talkDTOS.add(TalkDTO.toModel(talkEntity));
        }
        return talkDTOS;
    }

    @Override
    public List<TalkDTO> getByUser(UserEntity userEntity) throws Exception {
        List<TalkEntity> talkEntities = talkRepo.findAllByUsers(userEntity);
        List<TalkDTO> talkDTOS = new ArrayList<>();
        for (TalkEntity talkEntity: talkEntities){
            talkDTOS.add(TalkDTO.toModel(talkEntity));
        }
        return talkDTOS;
    }
}
