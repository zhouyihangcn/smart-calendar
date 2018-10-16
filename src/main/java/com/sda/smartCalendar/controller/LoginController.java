package com.sda.smartCalendar.controller;

import com.sda.smartCalendar.domain.IUserService;
import com.sda.smartCalendar.domain.OnRegistrationCompleteEvent;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.model.VerificationToken;
import com.sda.smartCalendar.domain.repository.RoleRepository;
import com.sda.smartCalendar.domain.repository.UserRepository;
import com.sda.smartCalendar.service.Autologin;
import com.sda.smartCalendar.social.providers.FacebookProvider;
import com.sda.smartCalendar.social.providers.GoogleProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class LoginController {

	@Autowired
	FacebookProvider facebookProvider;

	@Autowired
	GoogleProvider googleProvider;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private Autologin autologin;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Autowired
	private IUserService service;
	@Autowired
	private MessageSource messages;

	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
	public String loginToFacebook(Model model) {
		return facebookProvider.getFacebookUserData(model, new User("mojmail@.wp.pl", "Gosia", "Bak", "12345", "12345", "fb", 2L, true));
	}

	@RequestMapping(value = "/google", method = RequestMethod.GET)
	public String loginToGoogle(Model model) {
		return googleProvider.getGoogleUserData(model, new User("mojmail@.wp.pl", "Gosia", "Bak", "12345", "12345", "fb", 2L, true));
	}

	@RequestMapping(value = { "/", "/login" })
	public String login() {
		return "login";
	}

	@GetMapping("/registration")
	public String showRegistration(User user) {
		return "registration";
	}


	@PostMapping("/registration")
	public String registerUser(HttpServletResponse httpServletResponse, @Valid User user, Model model, BindingResult bindingResult,
							   WebRequest request, Errors errors) {
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		user.setProvider("REGISTRATION");
		// Save the details in DB
		if (StringUtils.isNotEmpty(user.getPassword())) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		model.addAttribute("loggedInUser", user);

		user.setRoles(Arrays.asList(roleRepository.findOne(2L)));

		autologin.setSecuritycontext(user);

		bindingResult.rejectValue("email", "message.regError");
		userRepository.save(user);
		//eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
		try {

//			HttpServletRequest req= (HttpServletRequest) request;
//			String appUrl= req.getRequestURI().substring(req.getContextPath().length());

			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent
					(user, request.getLocale(), appUrl));
		} catch (Exception me) {
		}

		return "secure/user";
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
