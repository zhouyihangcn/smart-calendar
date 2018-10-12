package com.sda.smartCalendar.social.providers;

import com.sda.smartCalendar.autologin.Autologin;
//import com.sda.smartCalendar.domain.model.Role;
import com.sda.smartCalendar.domain.model.User;
//import com.sda.smartCalendar.domain.repository.RoleRepository;
import com.sda.smartCalendar.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
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
//
//	@Autowired
//	private RoleRepository roleRepository;

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
		//user.setRoles(Arrays.asList(roleRepository.findOne(2L)));

		userRepository.save(user);

	}

	public void autoLoginUser(User user) {
		autologin.setSecuritycontext(user);
	}

	public Facebook getFacebook() {
		return facebook;
	}

	public void setFacebook(Facebook facebook) {
		this.facebook = facebook;
	}

	public ConnectionRepository getConnectionRepository() {
		return connectionRepository;
	}

	public void setConnectionRepository(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	public Google getGoogle() {
		return google;
	}

	public void setGoogle(Google google) {
		this.google = google;
	}

}
