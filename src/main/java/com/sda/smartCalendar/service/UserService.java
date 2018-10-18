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
import java.util.HashSet;
import java.util.Set;


@Service
public class UserService implements UserDetailsService,IUserService {

	@Autowired
	private UserRepository userRepository;
	

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		 if (user == null) {
	            throw new UsernameNotFoundException("No user found with email: " + email);
	        }
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("LOGGED_USER"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
	}
//////////////////

	@Autowired
	private VerificationTokenRepository tokenRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User getUser(final String verificationToken) {
		final VerificationToken token = tokenRepository.findByToken(verificationToken);
		if (token != null) {
			return token.getUser();
		}
		return null;
	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		return tokenRepository.findByToken(VerificationToken);
	}

	@Override
	public void saveRegisteredUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(token, user);
		tokenRepository.save(myToken);
	}
}
