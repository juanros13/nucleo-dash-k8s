package com.nucleodash.googlecalendar.model.dto;


import lombok.Data;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class Event {

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



}
