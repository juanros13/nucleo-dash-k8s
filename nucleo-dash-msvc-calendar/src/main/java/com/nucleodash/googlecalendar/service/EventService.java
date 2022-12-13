package com.nucleodash.googlecalendar.service;

import com.nucleodash.googlecalendar.model.dto.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<Event> list(String authId);
    Optional<Event> byId(Long id, String authId);
    Event save(Event calendar,String authId);
    void delete(Long id);

}
