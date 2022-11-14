package com.nucleo.dash.msvc.user.model.repository;


import com.nucleo.dash.msvc.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByAuthId(String authId);
}
