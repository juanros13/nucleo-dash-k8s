package com.nucleodash.googlecalendar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleCalendarGlobalException extends RuntimeException {

    private String code;
    private String message;

    public SimpleCalendarGlobalException(String message) {
        super(message);
    }
}
