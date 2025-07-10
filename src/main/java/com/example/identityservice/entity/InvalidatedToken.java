package com.example.identityservice.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
// @Table(name = "invalidatedTokens")
public class InvalidatedToken {
    @Id
    String id;

    Date expiryTime;
}
