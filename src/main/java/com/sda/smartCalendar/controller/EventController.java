package com.sda.smartCalendar.controller;


import com.sda.smartCalendar.domain.model.Event;
import com.sda.smartCalendar.domain.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
class EventController {


    private EventRepository eventRepositry;

    public EventController(EventRepository eventRepositry) {
        this.eventRepositry = eventRepositry;
    }

    @GetMapping("/events")
    Collection<Event> events() {
        return eventRepositry.findAll();
    }
    @GetMapping("/event/{id}")
    ResponseEntity<?> getGroup(@PathVariable UUID id) {
        Optional<Event> group = eventRepositry.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/event")
    ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) throws URISyntaxException {

        Event result = eventRepositry.save(event);
        return ResponseEntity.created(new URI("/api/event/" + result.getId()))
                .body(result);
    }

    @PutMapping("/event/{id}")
    ResponseEntity<Event> updateGroup(@PathVariable UUID id, @Valid @RequestBody Event event) {
        event.setId(id);

        Event result = eventRepositry.save(event);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable UUID id) {

        eventRepositry.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
