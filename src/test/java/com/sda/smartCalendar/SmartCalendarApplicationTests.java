package com.sda.smartCalendar;

import com.sda.smartCalendar.domain.IUserService;
import com.sda.smartCalendar.domain.model.Event;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.repository.RoleRepository;
import com.sda.smartCalendar.domain.repository.UserRepository;
import com.sda.smartCalendar.domain.repository.VerificationTokenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartCalendarApplicationTests {

	@Test
	public void contextLoads() {
	}

		@Autowired
		private IUserService userService;

		@Autowired
		private UserRepository userRepository;

		@Autowired
		private RoleRepository roleRepository;

		@Autowired
		private VerificationTokenRepository tokenRepository;

	@Test
	public void givenUserRegistered_whenCreateToken_thenCorrect() {
//		final User user = new User("mojmail@.wp.pl","Gosia","Bak","12345","12345", "fb", 2L, new Event(), true);
//		final String token = UUID.randomUUID().toString();
//		userService.createVerificationToken(user, token);
	}
}
