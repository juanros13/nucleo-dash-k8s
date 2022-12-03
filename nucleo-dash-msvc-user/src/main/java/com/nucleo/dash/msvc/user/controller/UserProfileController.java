package com.nucleo.dash.msvc.user.controller;

import com.nucleo.dash.msvc.user.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nucleo.dash.msvc.user.service.KeycloakUserService;
import com.nucleo.dash.msvc.user.service.UserProfileService;
import com.nucleo.dash.msvc.user.model.dto.GoogleCalendarIdRequest;
import com.nucleo.dash.msvc.user.model.dto.TokenGoogleCalendarRequest;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/userprofile")
@RequiredArgsConstructor
public class UserProfileController {

    private final KeycloakUserService keycloakUserService;
    private final UserProfileService userProfileService;

    @PostMapping(value = "/saveToken")
    public ResponseEntity saveToken(
            @RequestHeader("X-Auth-Id") String authIdHeader,
            @RequestBody TokenGoogleCalendarRequest tokenObject
    ) {

        UserEntity user =  userProfileService.saveToken(authIdHeader, tokenObject.getToken());
        return ResponseEntity.ok(user);
    }
    @PostMapping(value = "/saveIdCalendar")
    public ResponseEntity saveIdCalendar(
            @RequestHeader("X-Auth-Id") String authIdHeader,
            @RequestBody GoogleCalendarIdRequest idCalendar
    ) {
        log.info(authIdHeader);
        UserEntity user =  userProfileService.saveIdCalendar(authIdHeader, idCalendar.getId());
        return ResponseEntity.ok(user);
    }


}
