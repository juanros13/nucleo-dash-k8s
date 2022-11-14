package com.nucleo.dash.msvc.user.model.entity;


import com.nucleo.dash.msvc.user.model.dto.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authId;

    private String phone;



    @Enumerated(EnumType.STRING)
    private Status status;

    private String tokenGoogleCalendar;
    private String idGoogleCalendar;

}
