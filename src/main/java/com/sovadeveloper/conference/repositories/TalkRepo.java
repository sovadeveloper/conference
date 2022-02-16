package com.sovadeveloper.conference.repositories;

import com.sovadeveloper.conference.entities.TalkEntity;
import com.sovadeveloper.conference.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkRepo extends JpaRepository<TalkEntity, Long> {
    List<TalkEntity> findAllByUsers(UserEntity currentUser);
}
