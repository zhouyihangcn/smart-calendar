package com.sda.smartCalendar.controller.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
