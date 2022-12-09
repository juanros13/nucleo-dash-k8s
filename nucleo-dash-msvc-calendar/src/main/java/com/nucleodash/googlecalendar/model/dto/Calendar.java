package com.nucleodash.googlecalendar.model.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class Calendar {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String color;

    private Boolean visible;

}
