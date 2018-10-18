package com.sda.smartCalendar.service;

import com.sda.smartCalendar.controller.modelDTO.UserDTO;
import com.sda.smartCalendar.controller.modelDTO.UserRegistrationDTO;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.repository.RoleRepository;
import com.sda.smartCalendar.domain.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    public UserDTO findByEmail(String email) {
        UserDTO userDTO = mappingService.map(userRepository.findByEmail(email));
        return userDTO;
    }
}
