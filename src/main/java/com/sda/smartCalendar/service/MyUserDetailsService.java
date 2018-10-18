package com.sda.smartCalendar.service;

import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    public UserDetails loadUserByUsername(String email)
//            throws UsernameNotFoundException {
//
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        try {
//            User user = userRepository.findByEmail(email);
//            if (user == null) {
//                throw new UsernameNotFoundException(
//                        "No user found with username: " + email);
//            }
//
//            return new org.springframework.security.core.userdetails.User(
//                    user.getEmail(),
//                    user.getPassword().toLowerCase(),
//                    user.isEnabled(),
//                    accountNonExpired,
//                    credentialsNonExpired,
//                    accountNonLocked,
//                    Arrays.asList(new SimpleGrantedAuthority("2L")));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
