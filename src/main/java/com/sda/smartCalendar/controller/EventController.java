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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

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
    public String addPost(@ModelAttribute("eventDTO") @Valid EventDTO eventDTO, BindingResult bindingResult, Principal principal, Model model) {
        model.addAttribute("loggedInUser", userService.findByEmail(principal.getName()));
        if (bindingResult.hasErrors()) {
            return "addevent";
        }
        eventService.addEvent(eventDTO, principal);
        return "redirect:/showevents";
    }


    @GetMapping("/showevents")
    public String showEvents(Model model, Principal principal) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("eventList",eventService.getAllEventsByUser(principal.getName()));
        model.addAttribute("loggedInUser", userService.findByEmail(principal.getName()));
        return "showevents";
    }

    @GetMapping("/showevents/{id}")
    public String showEventDetails(@PathVariable("id") UUID id, Model model, Principal principal) {
        EventDTO eventDTO = eventService.findEventByID(id);
        model.addAttribute("eventDTO",eventDTO);
        model.addAttribute("categories", Category.values());
        model.addAttribute("eventList",eventService.getAllEventsByUser(principal.getName()));
        model.addAttribute("loggedInUser", userService.findByEmail(principal.getName()));
        return "eventdetails";
    }

    @GetMapping("/editevent/{id}")
    public String editEvent(@PathVariable("id") UUID id, Model model, Principal principal) {
        EventDTO eventDTO = eventService.findEventByID(id);
        model.addAttribute("eventDTO",eventDTO);
        model.addAttribute("categories", Category.values());
        model.addAttribute("loggedInUser", userService.findByEmail(principal.getName()));
        return "editevent";
    }





    @Transactional
    @GetMapping("/delete/{event}")
    public String deleteEvent(@PathVariable("event") UUID id){
        eventService.deleteEvent(id);
        return "redirect:/showevents";
    }


}
