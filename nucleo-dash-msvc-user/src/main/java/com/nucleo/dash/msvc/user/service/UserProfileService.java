package com.nucleo.dash.msvc.user.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import  com.nucleo.dash.msvc.user.model.entity.UserEntity;
import com.nucleo.dash.msvc.user.model.repository.UserRepository;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;


    public UserEntity saveToken(String authId, String token) {
        log.info(token);
        UserEntity userEntity = userRepository.findByAuthId(authId);
        userEntity.setTokenGoogleCalendar(token);
        userRepository.save(userEntity);
        return userEntity;
    }
    public UserEntity saveIdCalendar(String authId, String idCalendar) {
        log.info(idCalendar);
        UserEntity userEntity = userRepository.findByAuthId(authId);
        userEntity.setIdGoogleCalendar(idCalendar);
        userRepository.save(userEntity);
        return userEntity;
    }

}
