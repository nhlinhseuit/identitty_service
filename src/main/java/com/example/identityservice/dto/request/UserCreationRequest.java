package com.example.identityservice.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
//    Khong duoc truyen constant nen truyen String qua de lay key constant
    @Size(min = 2, message = "USERNAME_INVALID22")
     String username;

    @Size(min = 4, message = "PASSWORD_INVALID")
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
}
