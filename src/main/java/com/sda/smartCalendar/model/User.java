package com.sda.smartCalendar.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
//@EqualsAndHashCode(exclude = {"posts", "comments", "roles"})
//@ToString(exclude = {"posts", "comments", "roles"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nick;
    private String password;
}
