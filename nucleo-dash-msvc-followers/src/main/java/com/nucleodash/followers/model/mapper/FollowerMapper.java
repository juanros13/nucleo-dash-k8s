package com.nucleodash.followers.model.mapper;

import com.nucleodash.followers.model.dto.Followers;
import com.nucleodash.followers.model.entity.FollowersEntity;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class FollowerMapper extends BaseMapper<FollowersEntity, Followers>{

    @Override
    public FollowersEntity convertToEntity(Followers dto, Object... args) {
        FollowersEntity calendarEntity = new FollowersEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, calendarEntity);
        }
        return calendarEntity;
    }

    @Override
    public Followers convertToDto(FollowersEntity entity, Object... args) {
        Followers calendar = new Followers();
        if (entity != null) {
            BeanUtils.copyProperties(entity, calendar);
        }
        return calendar;
    }
    public Optional<Followers> convertToDtoOptional(FollowersEntity entity, Object... args) {
        Optional<Followers> calendar = Optional.of(new Followers());
        if (entity != null) {
            BeanUtils.copyProperties(entity, calendar);
        }
        return calendar;
    }
}
