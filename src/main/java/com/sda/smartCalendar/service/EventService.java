package com.sda.smartCalendar.service;

import com.sda.smartCalendar.controller.modelDTO.EventDTO;
import com.sda.smartCalendar.domain.model.Event;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.repository.EventRepository;
import com.sda.smartCalendar.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MappingService mappingService;

    public void addEvent(EventDTO eventDTO, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        Event event = null;
        event = mappingService.map(eventDTO);
        event.setUser(user);
        eventRepository.save(event);
    }

    public List<EventDTO> getAllEventsByUser(String email){
        List<Event> eventList = eventRepository.findAllByUserEmail(email);
        return eventList.stream()
                .map(event -> {
                    EventDTO eventDTO = mappingService.map(event);
                    return eventDTO;
                })
                .sorted(Comparator.comparing(EventDTO::getEvent_start).reversed())
                .collect(Collectors.toList());
    }

    public void deleteEvent(UUID id) {
        eventRepository.deleteEventById(id);
    }

    public EventDTO findEventByID(UUID id){
        EventDTO eventDTO = mappingService.map(eventRepository.findEventById(id));
        return eventDTO;
    }
}
