package com.example.identityservice.service;

import com.example.identityservice.dto.request.UserCreationRequest;
import com.example.identityservice.dto.response.UserResponse;
import com.example.identityservice.entity.User;
import com.example.identityservice.exception.AppException;
import com.example.identityservice.exception.ErrorCode;
import com.example.identityservice.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    private UserCreationRequest userCreationRequest;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;


    @BeforeEach
    void initData() {
        dob = LocalDate.of(2021, 1, 1);

        userCreationRequest = UserCreationRequest.builder()
                .username("john")
                .lastName("Smith")
                .firstName("John")
                .password("12345")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("c1qwesccaa")
                .username("john")
                .lastName("Smith")
                .firstName("John")
                .dob(dob)
                .build();

        user = User.builder()
                .id("c1qwesccaa")
                .username("john")
                .lastName("Smith")
                .firstName("John")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GIVEN
        when (userRepository.existsByUsername(anyString())).thenReturn(false);
        when (userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(userCreationRequest);

        // THEN
        assertThat(response.getId()).isEqualTo("c1qwesccaa");
        assertThat(response.getUsername()).isEqualTo("john");

    }

    @Test
    void createUser_userExisted_fail() {
        // GIVEN
        when (userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN
        // assert 1 exception
        var exeption = assertThrows(AppException.class, () ->
            userService.createUser(userCreationRequest)
        );

        assertThat(exeption.getErrorCode().getCode()).isEqualTo(1002);
        assertThat(exeption.getErrorCode().getMessage()).isEqualTo("Username already exists");
    }

    @Test
    @WithMockUser(username = "john")
    void getMyInfo_valid_success() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        var reponse = userService.getMyInfo();

        Assertions.assertThat(reponse.getUsername()).isEqualTo("john");
        Assertions.assertThat(reponse.getId()).isEqualTo("c1qwesccaa");
    }


    @Test
    @WithMockUser(username = "john")
    void getMyInfo_userNotFound_error() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));

        var exception = assertThrows(AppException.class, () ->userService.getMyInfo());

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1005);
    }
}
