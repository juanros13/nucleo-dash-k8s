package com.nucleodash.googlecalendar.model.rest.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {
        private Long id;

        private String email;

        private String password;

        private String authId;

        private String status;

        private String firstName;

        private String lastName;

        private String avatar;

        private String tokenGoogleCalendar;
        private String idGoogleCalendar;
    }
