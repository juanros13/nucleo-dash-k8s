package com.nucleodash.googlecalendar.model.repository;

import com.nucleodash.googlecalendar.model.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

    List<CalendarEntity> findByAuthId(String authId);

    CalendarEntity findByIdAndAuthId(Long id, String authId);


}
