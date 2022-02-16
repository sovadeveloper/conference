package com.sovadeveloper.conference.repositories;

import com.sovadeveloper.conference.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String name);
}
