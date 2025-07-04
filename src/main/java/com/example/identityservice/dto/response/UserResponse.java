package com.example.identityservice.dto.response;
import com.example.identityservice.entity.Role;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

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
