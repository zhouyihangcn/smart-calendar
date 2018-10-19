package com.sda.smartCalendar.domain.repository;

import com.sda.smartCalendar.domain.model.Event;
import com.sda.smartCalendar.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    Event findByName(String name);
    List<Event> findAllByUserEmail(String email);
}