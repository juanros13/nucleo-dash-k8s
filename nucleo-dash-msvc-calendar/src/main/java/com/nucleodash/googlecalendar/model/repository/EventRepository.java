package com.nucleodash.googlecalendar.model.repository;

import com.nucleodash.googlecalendar.model.entity.CalendarEntity;
import com.nucleodash.googlecalendar.model.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findByAuthId(String authId);

    Optional<EventEntity> findByIdAndAuthId(Long id, String authId);


}
