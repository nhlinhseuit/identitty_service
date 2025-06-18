package com.example.identityservice.dto.response;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    String id;
    String username;
    String password;
    String firstName;
    LocalDate dob;
    String lastName;
}
