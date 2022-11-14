package com.nucleo.dash.msvc.user.controller;

import com.nucleo.dash.msvc.user.model.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nucleo.dash.msvc.user.service.*;
import com.nucleo.dash.msvc.user.model.dto.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final KeycloakUserService keycloakUserService;
    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity createUser(@RequestBody User request) {
        log.info("Creating user with {}", request.toString());
        User user =  userService.createUser(request);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        log.info("Updating user with {}", userUpdateRequest.toString());
        return ResponseEntity.ok(userService.updateUser(userId, userUpdateRequest));
    }
    @PostMapping(value = "/recoverypassword")
    public ResponseEntity recoveryPassword(@RequestBody UserSendResetPasswordRequest email) {
        log.info("Send mail recovery {}", email.getEmail());
        return ResponseEntity.ok(userService.resetPassword(email.getEmail()));
    }
    @PostMapping(value = "/sendmailverify")
    public ResponseEntity sendMailVerify(@RequestBody UserSendResetPasswordRequest email) {
        log.info("Send mail verify {}", email.getEmail());
        return ResponseEntity.ok(userService.verifyMail(email.getEmail()));
    }
    @GetMapping
    public ResponseEntity readUsers(Pageable pageable) {
        log.info("Reading all users from API");
        return ResponseEntity.ok(userService.readUsers(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity readUser(@PathVariable("id") Long id) {
        log.info("Reading user by id {}", id);
        return ResponseEntity.ok(userService.readUser(id));
    }

    @PostMapping(value = "/updatepassword")
    public ResponseEntity readUserByAuthId(
            @RequestHeader("X-Auth-Id") String authIdHeader,
            @RequestBody UserPasswordRequest password
    ) {
        log.info("Update password by authIdHeader {}", authIdHeader);
        return ResponseEntity.ok(userService.updatePassword(authIdHeader,password));
    }
    @GetMapping(value = "/profile")
    public ResponseEntity readUserByAuthId(
            @RequestHeader("X-Auth-Id") String authIdHeader
    ) {
        log.info("Reading user by authIdHeader {}", authIdHeader);
        return ResponseEntity.ok(userService.readUserByAuthId(authIdHeader));
    }
}
