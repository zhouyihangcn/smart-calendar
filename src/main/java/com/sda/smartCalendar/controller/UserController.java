package com.sda.smartCalendar.controller;

import com.sda.smartCalendar.controller.modelDTO.EventDTO;
import com.sda.smartCalendar.controller.modelDTO.UserDTO;
import com.sda.smartCalendar.controller.modelDTO.UserRegistrationDTO;
import com.sda.smartCalendar.domain.IUserService;
import com.sda.smartCalendar.domain.OnRegistrationCompleteEvent;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.model.VerificationToken;
import com.sda.smartCalendar.domain.repository.UserRepository;
import com.sda.smartCalendar.service.MappingService;
import com.sda.smartCalendar.service.UserService;
import com.sda.smartCalendar.social.providers.FacebookProvider;
import com.sda.smartCalendar.social.providers.GoogleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;
import java.security.Principal;


@Controller
public class UserController {

    @Autowired
    private FacebookProvider facebookProvider;

    @Autowired
    private GoogleProvider googleProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MappingService mappingService;

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String loginToFacebook(Model model) {
        return facebookProvider.getFacebookUserData(model, new User());
    }

    @RequestMapping(value = "/google", method = RequestMethod.GET)
    public String loginToGoogle(Model model) {
        return googleProvider.getGoogleUserData(model, new User());
    }

    @RequestMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistration(UserRegistrationDTO userRegistrationDTO) {
        return "registration";
    }

    @GetMapping("/index")
    public String showMainPage(Model model, Principal principal) {
        model.addAttribute("loggedInUser", userService.findByEmail(principal.getName()));
        return "index";
    }


    @GetMapping("/nopage")
    public String nopage() {
        return "nopage";
    }


    /**
     * If we can't find a user/email combination
     */
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    //?
    @ModelAttribute("loggedInUser")
    public void secureUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        model.addAttribute("loggedInUser", user);
    }


    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private IUserService service;

    @Autowired
    private MessageSource messages;

    @PostMapping("/registration")
    public String registerUser(@Valid UserRegistrationDTO userRegistrationDTO, Model model, BindingResult bindingResult,
                               WebRequest request) {

        model.addAttribute("loggedInUser", userRegistrationDTO);
        //Do sprawdzenia
        bindingResult.rejectValue("email", "message.regError");

        User registered = userService.registerUser(userRegistrationDTO);
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                (registered, request.getLocale(), appUrl));
        return "login";
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {
        Locale locale = request.getLocale();
        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            //return "redirect:/badUser.html?lang=" + locale.getLanguage();
            return "redirect:/badUser?lang=" + locale.getLanguage();
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            //return "redirect:/badUser.html?lang=" + locale.getLanguage();
            return "redirect:/badUser?lang=" + locale.getLanguage();
        }
        user.setEnabled(true);

        service.saveRegisteredUser(user);
        //return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
        //return "redirect:/login?lang=" + request.getLocale().getLanguage();
        return "redirect:/login";
    }
}
