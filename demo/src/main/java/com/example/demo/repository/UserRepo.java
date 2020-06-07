package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Heshan
 * Created Date 6/6/2020
 */

public interface UserRepo extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}
