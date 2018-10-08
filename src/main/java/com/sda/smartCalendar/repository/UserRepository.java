package com.sda.smartCalendar.repository;

import com.sda.smartCalendar.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByNick(String nick);
//    User save(User user);
}
