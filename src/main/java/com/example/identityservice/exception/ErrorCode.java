package com.example.identityservice.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    INVALID_KEY(1001, "Uncategorized error"),
    USER_EXISTED(1002, "Username already exists"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters"),
    USER_NOT_EXISTED(1005, "User not existed"),
    UNAUTHENTICATED(1006, "Unauthenticated"),
    ;
    
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;

        this.message = message;
    }
}
