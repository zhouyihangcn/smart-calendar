package com.sda.smartCalendar.controller;

import com.sda.smartCalendar.controller.modelDTO.EventDTO;
import com.sda.smartCalendar.domain.model.Category;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.repository.UserRepository;
import com.sda.smartCalendar.service.EventService;
import com.sda.smartCalendar.service.UserService;
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
    private EventService eventService;

    @Autowired
    private UserService userService;

    @GetMapping("/addevent")
    public String addEvent(@ModelAttribute("eventDTO") EventDTO eventDTO, Model model, Principal principal) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("loggedInUser", userService.findByEmail(principal.getName()));
        return "addevent";
    }


    @PostMapping(path = "/addevent")
    public String addPost(@ModelAttribute("eventDTO") EventDTO eventDTO, Principal principal, Model model) {
        eventService.addEvent(eventDTO, principal);
        model.addAttribute("loggedInUser", userService.findByEmail(principal.getName()));
        return "redirect:/showevents";
    }


    @GetMapping("/showevents")
    public String showEvents(Model model, Principal principal) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("eventList",eventService.getAllEventsByUser(principal.getName()));
        model.addAttribute("loggedInUser", userService.findByEmail(principal.getName()));
        return "showevents";
    }

    @GetMapping("/calendar")
    public String showCalendar(Model model, Principal principal) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("eventList",eventService.getAllEventsByUser(principal.getName()));
        model.addAttribute("loggedInUser", userService.findByEmail(principal.getName()));
        return "calendar";
    }
}
