package com.sda.smartCalendar.domain;

import com.sda.smartCalendar.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private IUserService service;

//    @Autowired
//    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    // API


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                //= event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
          = event.getAppUrl() + "/registrationConfirm?token=" + token;
//        String message = messages.getMessage("message.regSucc", null, event.getLocale());
        String message = "Dziękujemy za rejestrację. Aby aktywować konto kliknij na poniższy link.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + "http://localhost:8080" + confirmationUrl);
        //email.setText("czesc");
        email.setReplyTo(env.getProperty("support.email"));
        email.setSentDate(new Date());
        email.setFrom(env.getProperty("support.email"));
        mailSender.send(email);
    }
}
