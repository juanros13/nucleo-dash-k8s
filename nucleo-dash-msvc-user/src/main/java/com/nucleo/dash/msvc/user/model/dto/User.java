package com.nucleo.dash.msvc.user.model.dto;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String email;

    private String password;

    private String authId;

    private Status status;

    private String firstName;

    private String lastName;

    private String avatar;
}
