package com.sda.smartCalendar.domain.model;

import java.io.Serializable;
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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

	//private static final long serialVersionUID = 1L;
	@Id
	@NotNull(message = "Email  cannot be empty")
	@Email(message = "Email Format is not valid")
	@Size(min = 3, max = 30, message = "Email can not be empty")
	private String email;

	@NotNull(message = "First Name cannot be empty")
	@Size(min = 3, max = 30, message = "First Name cannot be less than 3 characters")
	private String firstName;

	@NotNull(message = "Last Name cannot be empty")
	@Size(min = 3, max = 30, message = "Last Name cannot be less than 3 characters")
	private String lastName;

	private String password;


	@Transient
	private String passwordConfirm;
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

}
