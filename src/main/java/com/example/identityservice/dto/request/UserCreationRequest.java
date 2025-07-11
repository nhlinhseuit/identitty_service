package com.example.identityservice.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

import com.example.identityservice.validation.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    //    Khong duoc truyen constant nen truyen String qua de lay key constant
    @Size(min = 2, message = "USERNAME_INVALID")
    String username;

    @Size(min = 4, message = "PASSWORD_INVALID")
    String password;

    String firstName;
    String lastName;

    @DobConstraint(min = 2, message = "INVALID_DOB")
    LocalDate dob;
}
