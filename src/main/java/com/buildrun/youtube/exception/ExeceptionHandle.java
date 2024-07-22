package com.buildrun.youtube.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExeceptionHandle {

    @ExceptionHandler( UserNotFoundExeception.class )
    public ResponseEntity userNotFoundHandleException(UserNotFoundExeception e){
        ApiError error = new ApiError(HttpStatus.NOT_FOUND,"User not found");
        return new ResponseEntity(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( UserAlreadyExistExeception.class )
    public ResponseEntity UserAlreadyExistExeception(UserAlreadyExistExeception e){
        ApiError error = new ApiError(HttpStatus.CONFLICT,"User Already exists");
        return new ResponseEntity(error,HttpStatus.CONFLICT);
    }

}
