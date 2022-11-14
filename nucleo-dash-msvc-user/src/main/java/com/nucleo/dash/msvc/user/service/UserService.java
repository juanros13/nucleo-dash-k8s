package com.nucleo.dash.msvc.user.service;


import com.nucleo.dash.msvc.user.exception.EntityNotFoundException;
import com.nucleo.dash.msvc.user.model.dto.Status;
import com.nucleo.dash.msvc.user.model.dto.User;
import com.nucleo.dash.msvc.user.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.nucleo.dash.msvc.user.model.repository.UserRepository;
import com.nucleo.dash.msvc.user.service.rest.BankingCoreRestClient;
import com.nucleo.dash.msvc.user.exception.UserAlreadyRegisteredException;
import com.nucleo.dash.msvc.user.exception.GlobalErrorCode;
import com.nucleo.dash.msvc.user.model.mapper.UserMapper;
import com.nucleo.dash.msvc.user.model.dto.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final KeycloakUserService keycloakUserService;
    private final UserRepository userRepository;
    private final BankingCoreRestClient bankingCoreRestClient;

    private UserMapper userMapper = new UserMapper();


    public User resetPassword(String email) {
        UserRepresentation userRepresentation = keycloakUserService.getByEmail(email);

        if(userRepresentation==null) {
            throw new UserAlreadyRegisteredException("No se encontro el usuario seleccionado", GlobalErrorCode.ERROR_EMAIL_REGISTERED);
        }
        log.info(userRepresentation.getId());
        UserEntity userEntity = userRepository.findByAuthId(userRepresentation.getId());
        log.info(userRepresentation.getId());
        keycloakUserService.credentialRest(userRepresentation);
        User user  = new User();
        user.setEmail(userRepresentation.getEmail());
        user.setFirstName(userRepresentation.getFirstName());
        user.setLastName(userRepresentation.getLastName());
        user.setAuthId(userRepresentation.getId());

        return user;
    }
    public User verifyMail(String email) {
        UserRepresentation userRepresentation = keycloakUserService.getByEmail(email);

        if(userRepresentation==null) {
            throw new UserAlreadyRegisteredException("No se encontro el usuario seleccionado", GlobalErrorCode.ERROR_EMAIL_REGISTERED);
        }
        log.info(userRepresentation.getId());
        UserEntity userEntity = userRepository.findByAuthId(userRepresentation.getId());
        log.info(userRepresentation.getId());
        keycloakUserService.emailVerified(userRepresentation);
        User user  = new User();
        user.setEmail(userRepresentation.getEmail());
        user.setFirstName(userRepresentation.getFirstName());
        user.setLastName(userRepresentation.getLastName());
        user.setAuthId(userRepresentation.getId());

        return user;
    }
    public User createUser(User user) {
        log.info("Entrando 1");
        List<UserRepresentation> userRepresentations = keycloakUserService.readUserByEmail(user.getEmail());
        log.info("Entrando 2");
        if (userRepresentations.size() > 0) {
            throw new UserAlreadyRegisteredException("Este correo electronico ya ha sido registrado. Favor de revisar y volver a intentar.", GlobalErrorCode.ERROR_EMAIL_REGISTERED);
        }
        log.info("Entrando 3");


        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEmailVerified(false);
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(user.getEmail());

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(user.getPassword());
        credentialRepresentation.setTemporary(false);
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
        log.info("Entrando 4");
        Integer userCreationResponse = keycloakUserService.createUser(userRepresentation);
        log.info("Entrando 5");

        if (userCreationResponse == 201) {
            log.info("User created under given username {}", user.getEmail());
            log.info("User created under given first {}", user.getFirstName());
            log.info("User created under given last {}", user.getLastName());
            //keycloakUserService.emailVerified(userRepresentation);
            List<UserRepresentation> userRepresentations1 = keycloakUserService.readUserByEmail(user.getEmail());
            user.setAuthId(userRepresentations1.get(0).getId());
            user.setStatus(Status.PENDING);
            UserEntity save = userRepository.save(userMapper.convertToEntity(user));
            user.setPassword(null);
            return user;
        }
        throw new UserAlreadyRegisteredException("Imposible crear el usuario.", GlobalErrorCode.ERROR_EMAIL_REGISTERED);

    }

    public List<User> readUsers(Pageable pageable) {
        Page<UserEntity> allUsersInDb = userRepository.findAll(pageable);
        List<User> users = userMapper.convertToDtoList(allUsersInDb.getContent());
        users.forEach(user -> {
            UserRepresentation userRepresentation = keycloakUserService.readUser(user.getAuthId());
            user.setId(user.getId());
            user.setEmail(userRepresentation.getEmail());
        });
        return users;
    }

    public User readUser(Long userId) {
        return userMapper.convertToDto(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
    }
    public User readUserByAuthId(String authId) {
        UserRepresentation userRepresentation = keycloakUserService.readUser(authId);
        log.info(userRepresentation.getFirstName());
        log.info(userRepresentation.getLastName());
        log.info(userRepresentation.getEmail());
        User user = new User();
        user.setFirstName(userRepresentation.getFirstName());
        user.setLastName(userRepresentation.getLastName());
        user.setEmail(userRepresentation.getEmail());
        user.setAuthId(authId);
        return user;
    }

    public User updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (userUpdateRequest.getStatus() == Status.APPROVED) {
            UserRepresentation userRepresentation = keycloakUserService.readUser(userEntity.getAuthId());
            userRepresentation.setEnabled(true);
            userRepresentation.setEmailVerified(true);
            keycloakUserService.updateUser(userRepresentation);
        }

        userEntity.setStatus(userUpdateRequest.getStatus());
        return userMapper.convertToDto(userRepository.save(userEntity));
    }
    public User updatePassword(String authId, UserPasswordRequest password) {
        UserRepresentation userRepresentation = keycloakUserService.changePassword(authId,password.getPassword());

        User user = new User();
        user.setFirstName(userRepresentation.getFirstName());
        user.setLastName(userRepresentation.getLastName());
        user.setEmail(userRepresentation.getEmail());
        user.setAuthId(authId);
        return user;
    }

}
