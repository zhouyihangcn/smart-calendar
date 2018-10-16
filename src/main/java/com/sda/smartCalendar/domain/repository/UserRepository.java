package com.sda.smartCalendar.domain.repository;

import com.sda.smartCalendar.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}
