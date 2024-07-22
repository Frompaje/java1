package com.buildrun.youtube.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserNotFoundExeception extends RuntimeException {
    private String message;

    public UserNotFoundExeception(String message) {
        super( message );
        this.message = message;
    }
}