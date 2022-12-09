package com.nucleodash.googlecalendar.service;

import com.nucleodash.googlecalendar.model.dto.Calendar;
import com.nucleodash.googlecalendar.model.entity.CalendarEntity;

import java.util.List;
import java.util.Optional;

public interface CalendarService {

    List<Calendar> list(String authId);
    Optional<Calendar> byId(Long id, String authId);
    Calendar save(Calendar calendar,String authId);
    void delete(Long id);

}
