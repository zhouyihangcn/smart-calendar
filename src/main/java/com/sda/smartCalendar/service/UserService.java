package com.sda.smartCalendar.service;

import com.sda.smartCalendar.controller.modelDTO.UserDTO;
import com.sda.smartCalendar.controller.modelDTO.UserRegistrationDTO;
import com.sda.smartCalendar.domain.IUserService;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.model.VerificationToken;
import com.sda.smartCalendar.domain.repository.RoleRepository;
import com.sda.smartCalendar.domain.repository.UserRepository;
import com.sda.smartCalendar.domain.repository.VerificationTokenRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registerUser(UserRegistrationDTO userRegistrationDTO) {

        if (StringUtils.isNotEmpty(userRegistrationDTO.getPassword())) {
            userRegistrationDTO.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
        }
        userRegistrationDTO.setProvider("REGISTRATION");
        User user = mappingService.map(userRegistrationDTO);
        user.setRoles(Arrays.asList(roleRepository.findOne(2L)));
        userRepository.save(user);
        return user;
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }
    @Override
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
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

    @Override
    public User findUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid nick or password.");
        }
        return user;
    }
    public void editProfile(UserRegistrationDTO userDTO, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());

        if(userDTO.getFirstName() != null){
            user.setFirstName(userDTO.getFirstName());
        }
        if(userDTO.getLastName() != null){
            user.setLastName(userDTO.getLastName());
        }
        if(userDTO.getPhoneNumber() != null){
            user.setPhoneNumber(userDTO.getPhoneNumber());
        }
        userRepository.save(user);
    }
}
