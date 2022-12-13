package com.nucleodash.googlecalendar.model.mapper;

import com.nucleodash.googlecalendar.model.dto.Event;
import com.nucleodash.googlecalendar.model.entity.EventEntity;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class EventMapper extends BaseMapper<EventEntity, Event>{

    @Override
    public EventEntity convertToEntity(Event dto, Object... args) {
        EventEntity eventEntity = new EventEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, eventEntity);
        }
        return eventEntity;
    }

    @Override
    public Event convertToDto(EventEntity entity, Object... args) {
        Event event = new Event();
        if (entity != null) {
            BeanUtils.copyProperties(entity, event);
        }
        return event;
    }
    public Optional<Event> convertToDtoOptional(EventEntity entity, Object... args) {
        Optional<Event> event = Optional.of(new Event());
        if (entity != null) {
            BeanUtils.copyProperties(entity, event);
        }
        return event;
    }
}
