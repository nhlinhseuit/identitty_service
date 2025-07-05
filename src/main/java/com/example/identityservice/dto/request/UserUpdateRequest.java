package com.example.identityservice.dto.request;

import com.example.identityservice.validation.DobConstraint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserUpdateRequest {
    private String password;
    private String firstName;
    private String lastName;

    @DobConstraint(min = 2, message = "INVALID_DOB")
    private LocalDate dob;
    List<String> roles;
}
