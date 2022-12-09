package com.nucleodash.googlecalendar.service;

import com.nucleodash.googlecalendar.exception.EntityNotFoundException;
import com.nucleodash.googlecalendar.model.dto.Calendar;
import com.nucleodash.googlecalendar.model.entity.CalendarEntity;
import com.nucleodash.googlecalendar.model.mapper.CalendarMapper;
import com.nucleodash.googlecalendar.model.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CalendarServiceImpl implements CalendarService{

    private CalendarMapper calendarMapper = new CalendarMapper();

    @Autowired
    private CalendarRepository calendarRepository;

    @Override
    public List<Calendar> list(String authId) {
        return new ArrayList<>(calendarMapper.convertToDto(calendarRepository.findByAuthId(authId)));
    }

    @Override
    public Optional<Calendar> byId(Long id, String authId) {
        return  Optional.of(calendarMapper.convertToDto(calendarRepository.findByIdAndAuthId(id, authId).orElseThrow(EntityNotFoundException::new)));
    }

    @Override
    public Calendar save(Calendar calendar, String authId) {
        CalendarEntity calendarEntity = calendarMapper.convertToEntity(calendar);
        calendarEntity.setAuthId(authId);
        return calendarMapper.convertToDto(calendarRepository.save(calendarEntity));
    }

    @Override
    public void delete(Long id) {
        calendarRepository.deleteById(id);
    }


}
