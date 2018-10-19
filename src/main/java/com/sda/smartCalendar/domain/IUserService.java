package com.sda.smartCalendar.domain;

import com.sda.smartCalendar.controller.modelDTO.UserRegistrationDTO;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.model.VerificationToken;
import com.sda.smartCalendar.exceptions.EmailExistsException;

public interface IUserService {

    User registerUser(UserRegistrationDTO userRegistrationDTO)
            throws EmailExistsException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

}