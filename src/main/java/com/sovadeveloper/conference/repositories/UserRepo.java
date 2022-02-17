package com.sovadeveloper.conference.repositories;

import com.sovadeveloper.conference.entities.Role;
import com.sovadeveloper.conference.entities.TalkEntity;
import com.sovadeveloper.conference.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String name);
    List<UserEntity> findAllByTalksNotContainingAndRole(TalkEntity talkEntity, Role role);
}
