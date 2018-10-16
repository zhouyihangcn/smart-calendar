package com.sda.smartCalendar.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sda.smartCalendar.domain.repository.UserRepository;
import com.sda.smartCalendar.service.Autologin;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.repository.RoleRepository;
import com.sda.smartCalendar.social.providers.FacebookProvider;
import com.sda.smartCalendar.social.providers.GoogleProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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

	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
	public String loginToFacebook(Model model) {
		return facebookProvider.getFacebookUserData(model, new User());
	}

	@RequestMapping(value = "/google", method = RequestMethod.GET)
	public String loginToGoogle(Model model) {
		return googleProvider.getGoogleUserData(model, new User());
	}

	@RequestMapping(value = { "/", "/login" })
	public String login() {
		return "login";
	}

	@GetMapping("/registration")
	public String showRegistration(User user) {
		return "registration";
	}

//	-----------------------------
	@GetMapping("/index")
	public String bla() {
		return "index";
	}
//	-----------------------------

	@PostMapping("/registration")
	public String registerUser(HttpServletResponse httpServletResponse, @Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		user.setProvider("REGISTRATION");
		// Save the details in DB
		if (StringUtils.isNotEmpty(user.getPassword())) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}

		user.setRoles(Arrays.asList(roleRepository.findOne(2L)));
		userRepository.save(user);
		autologin.setSecuritycontext(user);

		model.addAttribute("loggedInUser", user);
		return "login";
	}

	/** If we can't find a user/email combination */
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

}
