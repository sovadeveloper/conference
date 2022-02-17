package com.sovadeveloper.conference.services;

import com.sovadeveloper.conference.dto.TalkDTO;
import com.sovadeveloper.conference.entities.TalkEntity;
import com.sovadeveloper.conference.entities.UserEntity;

import java.util.List;

public interface TalkService {
    TalkDTO create(TalkEntity talkEntity, UserEntity userEntity) throws Exception;
    TalkDTO getById(Long id) throws Exception;
    TalkDTO edit(Long id, TalkEntity talkEntityUpdated) throws Exception;
    Long delete(Long id) throws Exception;
    TalkDTO deleteUserFromTalk(TalkEntity talkEntityUpdated) throws Exception;
    TalkDTO addUserToTalk(TalkEntity talkEntityUpdated) throws Exception;
    List<TalkDTO> getAll() throws Exception;
    List<TalkDTO> getByUser(UserEntity userEntity) throws Exception;
}
