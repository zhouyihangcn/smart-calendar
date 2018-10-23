package com.sda.smartCalendar.domain.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String provider;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_email", referencedColumnName = "email"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

	@OneToMany (mappedBy = "user")
	private Set<Event> events = new HashSet<>();

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "phone_number")
	private String phoneNumber;
}
