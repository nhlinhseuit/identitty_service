package com.example.identityservice.dto.response;

import com.example.identityservice.entity.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    String name;
    String description;
    Set<PermissionResponse> permissions;
}
