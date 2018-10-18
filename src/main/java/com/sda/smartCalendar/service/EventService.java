package com.sda.smartCalendar.service;

import com.sda.smartCalendar.controller.modelDTO.EventDTO;
import com.sda.smartCalendar.domain.model.Event;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.repository.EventRepository;
import com.sda.smartCalendar.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MappingService mappingService;

    public void addEvent(EventDTO eventDTO, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        Event event = null;
        event = mappingService.map(eventDTO);
        event.setUser(user);
        eventRepository.save(event);
    }


}
