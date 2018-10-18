package com.sda.smartCalendar.domain.repository;

import com.sda.smartCalendar.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    Event findByName(String name);
}