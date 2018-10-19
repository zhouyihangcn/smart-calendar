package com.sda.smartCalendar.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.servlet.LocaleResolver;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Locale;

@Configuration
public class LocalConfiguration {

    @Bean
    LocaleResolver createLocal(){
        LocaleResolver localeResolver = new LocaleResolver() {
            @Override
            public Locale resolveLocale(HttpServletRequest httpServletRequest) {
                return null;
            }

            @Override
            public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
            }
        };
        return localeResolver;
    }
}
