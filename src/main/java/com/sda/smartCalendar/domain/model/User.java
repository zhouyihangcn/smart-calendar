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
	@NotNull(message = "Pole nie może być puste")
	@Email(message = "Format niepoprawny")
	@Size(min = 3, max = 30, message = "Pole musi zawierać od 3 do 30 znaków")
	private String email;

	@NotNull(message = "Pole nie może być puste")
	@Size(min = 3, max = 30, message = "Pole musi zawierać od 3 do 30 znaków")
	private String firstName;

	@NotNull(message = "Pole nie może być puste")
	@Size(min = 3, max = 30, message = "Pole musi zawierać od 3 do 30 znaków")
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
