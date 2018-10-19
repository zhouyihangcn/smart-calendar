//package com.sda.smartCalendar.service;
//
//import com.sda.smartCalendar.controller.modelDTO.UserRegistrationDTO;
//import com.sda.smartCalendar.domain.IUserService;
//import com.sda.smartCalendar.domain.model.Role;
//import com.sda.smartCalendar.domain.model.User;
//import com.sda.smartCalendar.domain.model.VerificationToken;
//import com.sda.smartCalendar.domain.repository.RoleRepository;
//import com.sda.smartCalendar.domain.repository.UserRepository;
//import com.sda.smartCalendar.domain.repository.VerificationTokenRepository;
//import com.sda.smartCalendar.exceptions.EmailExistsException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Arrays;
//
//@Service
//@Transactional
//public class MUserService implements IUserService {
//
//    @Autowired
//    private UserRepository repository;
//
//    @Autowired
//    private VerificationTokenRepository tokenRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    private boolean emailExist(String email) {
//        User user = repository.findByEmail(email);
//        if (user != null) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public User registerUser(UserRegistrationDTO userRegistrationDTO)  {
//        return null;
//    }
//
//    @Override
//    public User getUser(String verificationToken) {
//        User user = tokenRepository.findByToken(verificationToken).getUser();
//        return user;
//    }
//
//    @Override
//    public VerificationToken getVerificationToken(String VerificationToken) {
//        return tokenRepository.findByToken(VerificationToken);
//    }
//
//    @Override
//    public void saveRegisteredUser(User user) {
//        repository.save(user);
//    }
//
//    @Override
//    public void createVerificationToken(User user, String token) {
//        VerificationToken myToken = new VerificationToken(token, user);
//        tokenRepository.save(myToken);
//    }
//}