package com.sda.smartCalendar.domain.repository;

import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.domain.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, UUID> {

    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
