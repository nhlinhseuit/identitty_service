package com.example.identityservice.dto.response;

import java.time.LocalDate;
import java.util.Set;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    String id;
    String username;
    String firstName;
    LocalDate dob;
    String lastName;
    Set<RoleResponse> roles;
}
