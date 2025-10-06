package com.example.identityservice.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.identityservice.entity.Permission;
import com.example.identityservice.entity.Role;
import com.example.identityservice.entity.User;
import com.example.identityservice.repository.PermissionRepository;
import com.example.identityservice.repository.RoleRepository;
import com.example.identityservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Bean
    @ConditionalOnProperty(prefix = "spring.datasource", name = "driver-class-name", havingValue = "org.postgresql.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            // Create APPROVE_POST permission if not exists
            if (permissionRepository.findById("APPROVE_POST").isEmpty()) {
                Permission permission = Permission.builder()
                        .name("APPROVE_POST")
                        .description("Approve post permission")
                        .build();
                permissionRepository.save(permission);
                log.warn("APPROVE_POST permission created");
            }

            // Create ADMIN role with APPROVE_POST permission if not exists
            if (roleRepository.findById("ADMIN").isEmpty()) {
                Set<Permission> permissions = new HashSet<>();
                permissionRepository.findById("APPROVE_POST").ifPresent(permissions::add);

                Role adminRole = Role.builder()
                        .name("ADMIN")
                        .description("Admin role")
                        .permissions(permissions)
                        .build();
                roleRepository.save(adminRole);
                log.warn("ADMIN role created with APPROVE_POST permission");
            }

            // Delete existing admin user if exists
            userRepository.findByUsername("admin").ifPresent(existingAdmin -> {
                userRepository.delete(existingAdmin);
                log.warn("Existing admin user deleted");
            });

            // Create new admin user with ADMIN role
            Role adminRole = roleRepository.findById("ADMIN").orElse(null);

            Set<Role> roles = new HashSet<>();
            if (adminRole != null) {
                roles.add(adminRole);
            }

            User user = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .roles(roles)
                    .build();

            userRepository.save(user);
            log.warn("New admin user has been created with password: admin, please change it");
        };
    }
}
