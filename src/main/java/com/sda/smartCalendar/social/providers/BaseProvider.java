package com.sda.smartCalendar.social.providers;

import com.sda.smartCalendar.domain.repository.RoleRepository;
import com.sda.smartCalendar.domain.repository.UserRepository;
import com.sda.smartCalendar.service.Autologin;
import com.sda.smartCalendar.domain.model.User;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;

import java.util.Arrays;

@Data
@Configuration
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseProvider {

	private Facebook facebook;
	private Google google;
	private ConnectionRepository connectionRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	protected Autologin autologin;

	public BaseProvider(Facebook facebook, Google google, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
		this.google = google;
	}

	protected void saveUserDetails(User user) {
		if (StringUtils.isNotEmpty(user.getPassword())) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		user.setRoles(Arrays.asList(roleRepository.findOne(2L)));
		userRepository.save(user);
	}

	public void autoLoginUser(User user) {
		autologin.setSecuritycontext(user);
	}
}
