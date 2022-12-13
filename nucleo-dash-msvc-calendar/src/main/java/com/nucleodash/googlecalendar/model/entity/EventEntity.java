package com.nucleodash.googlecalendar.model.entity;


import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer calendarId;

    @NotNull
    private Integer recurringEventId;

    @NotNull
    private Boolean isFirstInstance;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private Date startDay;

    private Date endDay;

    private Boolean allDay;

    private String recurrence;

    @NotBlank
    private String authId;

}
