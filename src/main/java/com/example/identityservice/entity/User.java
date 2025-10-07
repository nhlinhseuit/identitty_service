package com.example.identityservice.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "username", unique = true, columnDefinition = "CITEXT")
    String username;

    String password;
    String firstName;
    LocalDate dob;
    String lastName;

    @ManyToMany // user co nhieu roles -> chi can khai bao ManyToMany o day va db se tu sinh bang, kh can khai bao trong
    // role ve ManyToMany voi User nua
    Set<Role> roles;
}
 