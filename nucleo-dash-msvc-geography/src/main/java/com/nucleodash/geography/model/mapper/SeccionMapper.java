package com.nucleodash.geography.model.mapper;

import com.nucleodash.geography.model.dto.Seccion;
import com.nucleodash.geography.model.entity.SeccionEntity;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class SeccionMapper extends BaseMapper<SeccionEntity, Seccion> {

    @Override
    public SeccionEntity convertToEntity(Seccion dto, Object... args) {
        SeccionEntity calendarEntity = new SeccionEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, calendarEntity);
        }
        return calendarEntity;
    }

    @Override
    public Seccion convertToDto(SeccionEntity entity, Object... args) {
        Seccion calendar = new Seccion();
        if (entity != null) {
            BeanUtils.copyProperties(entity, calendar);
        }
        return calendar;
    }
    public Optional<Seccion> convertToDtoOptional(SeccionEntity entity, Object... args) {
        Optional<Seccion> calendar = Optional.of(new Seccion());
        if (entity != null) {
            BeanUtils.copyProperties(entity, calendar);
        }
        return calendar;
    }
}
