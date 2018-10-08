package com.sda.smartCalendar.controller;

import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User userRegistration(){
        return new User();
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") User user,
                                      BindingResult result)   {
        if (userService.findByNick(user.getNick()) != null) {
            return "redirect:/registration?failed";
        }
        if (result.hasErrors()) {
            return "registration";
        }
        userService.saveUser(user);
        return "redirect:/registration?success";
    }

}
