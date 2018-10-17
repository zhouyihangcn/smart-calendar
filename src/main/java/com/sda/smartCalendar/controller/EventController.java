package com.sda.smartCalendar.controller;

import com.sda.smartCalendar.controller.modelDTO.EventDTO;
import com.sda.smartCalendar.domain.model.Category;
import com.sda.smartCalendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/addEvent")
    public String addEvent(@ModelAttribute("eventDTO") EventDTO eventDTO, Model model) {
        model.addAttribute("categories", Category.values());
        return "addEvent";
    }

    @PostMapping(path = "/addEvent")
    public String addPost(@ModelAttribute("eventDTO") EventDTO eventDTO, Principal principal) {
        eventService.addEvent(eventDTO, principal);
        return "redirect:/index";
    }
}
