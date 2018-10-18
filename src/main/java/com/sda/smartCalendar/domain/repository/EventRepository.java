package com.sda.smartCalendar.domain.repository;

import com.sda.smartCalendar.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    Event findByName(String name);
}