package com.sda.smartCalendar.service;


import com.sda.smartCalendar.domain.IUserService;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.model.VerificationToken;
import com.sda.smartCalendar.domain.repository.RoleRepository;
import com.sda.smartCalendar.domain.repository.UserRepository;
import com.sda.smartCalendar.domain.repository.VerificationTokenRepository;
import com.sda.smartCalendar.exceptions.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		try {
			User user = userRepository.findByEmail(email);
			if (user == null) {
				throw new UsernameNotFoundException(
						"No user found with username: " + email);
			}
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			grantedAuthorities.add(new SimpleGrantedAuthority("LOGGED_USER"));

			return new org.springframework.security.core.userdetails.User(
					user.getEmail(),
					user.getPassword(),
					user.isEnabled(),
					accountNonExpired,
				credentialsNonExpired,
				accountNonLocked,
					grantedAuthorities);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
