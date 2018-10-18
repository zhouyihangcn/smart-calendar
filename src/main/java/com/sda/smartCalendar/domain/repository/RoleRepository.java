package com.sda.smartCalendar.domain.repository;


import com.sda.smartCalendar.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


}