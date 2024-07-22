package com.buildrun.youtube.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAlreadyExistExeception extends  RuntimeException {
    private String message;

    public UserAlreadyExistExeception(String message) {
        super( message );
        this.message = message;
    }
}
