package com.nucleodash.googlecalendar.model.mapper;

import com.nucleodash.googlecalendar.model.dto.Calendar;
import com.nucleodash.googlecalendar.model.entity.CalendarEntity;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class CalendarMapper extends BaseMapper<CalendarEntity, Calendar>{

    @Override
    public CalendarEntity convertToEntity(Calendar dto, Object... args) {
        CalendarEntity calendarEntity = new CalendarEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, calendarEntity);
        }
        return calendarEntity;
    }

    @Override
    public Calendar convertToDto(CalendarEntity entity, Object... args) {
        Calendar calendar = new Calendar();
        if (entity != null) {
            BeanUtils.copyProperties(entity, calendar);
        }
        return calendar;
    }
    public Optional<Calendar> convertToDtoOptional(CalendarEntity entity, Object... args) {
        Optional<Calendar> calendar = Optional.of(new Calendar());
        if (entity != null) {
            BeanUtils.copyProperties(entity, calendar);
        }
        return calendar;
    }
}
