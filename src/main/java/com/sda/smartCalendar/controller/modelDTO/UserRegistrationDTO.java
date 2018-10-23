package com.sda.smartCalendar.controller.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    @NotEmpty(message = "Pole nie może być puste")
    @Email(message = "Format niepoprawny")
    @Size(min = 3, max = 30, message = "Pole musi zawierać od 3 do 30 znaków")
    private String email;

    @NotEmpty(message = "Pole nie może być puste")
    @Size(min = 3, max = 30, message = "Pole musi zawierać od 3 do 30 znaków")
    private String firstName;

    @NotEmpty(message = "Pole nie może być puste")
    @Size(min = 3, max = 30, message = "Pole musi zawierać od 3 do 30 znaków")
    private String lastName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordConfirm;

    private String provider;

    @AssertTrue
    private Boolean terms;

    @NotEmpty
    private String phoneNumber;
}

